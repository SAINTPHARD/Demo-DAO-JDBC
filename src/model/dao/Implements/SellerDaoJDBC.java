package model.dao.Implements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection conn;

	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	// =========== Método CRUD: Create, Read, Update, Delete ===============
	// INSERT
	// ==========================
	@Override
	public void insert(Seller obj) {
		String sql = "INSERT INTO seller " + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
				+ "VALUES (?, ?, ?, ?, ?)";

		try (PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			// Setando os parâmetros da consulta
			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setDate(3, java.sql.Date.valueOf(obj.getBirthDate()));
			st.setDouble(4, obj.getBaseSalary());
			st.setInt(5, obj.getDepartment().getId());

			int rowsAffected = st.executeUpdate();

			// Verifica se a inserção foi bem-sucedida
			if (rowsAffected > 0) { // Se rowsAffected for maior que 0, a inserção foi bem-sucedida
				try (ResultSet rs = st.getGeneratedKeys()) { // Obtém as chaves geradas (IDs)
					if (rs.next()) {
						obj.setId(rs.getInt(1)); // Define o ID do objeto com a chave gerada
					}
					DB.closeResultSet(rs); // Fecha o ResultSet-
				}
			} else {
				throw new DbException("Erro inesperado: nenhuma linha inserida!");
			}
		} catch (SQLException e) {
			throw new DbException("Erro ao inserir vendedor: " + e.getMessage());
		}
	}

	// ==========================
	// UPDATE
	// ==========================
	@Override
	public void update(Seller obj) {
		String sql = "UPDATE seller " 
				+ "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
				+ "WHERE Id = ?";

		try (PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setDate(3, java.sql.Date.valueOf(obj.getBirthDate()));
			st.setDouble(4, obj.getBaseSalary());
			st.setInt(5, obj.getDepartment().getId());
			st.setInt(6, obj.getId());

			int rowsAffected = st.executeUpdate();

			if (rowsAffected == 0) {
				throw new DbException("Atualização falhou: ID não encontrado!");
			}

		} catch (SQLException e) {
			throw new DbException("Erro ao atualizar vendedor: " + e.getMessage());
		}
	}

	// ==========================
	// --- IMPLEMENTAÇÃO DO DELETE ---
	// ==========================
	@Override
	public void deleteById(Integer id) {
		String sql = "DELETE FROM seller WHERE Id = ?"; // SQL para deletar o vendedor com o ID especificado

		try (PreparedStatement st = conn.prepareStatement(sql)) {
			st.setInt(1, id);

			int rowsAffected = st.executeUpdate();

			if (rowsAffected == 0) {
				throw new DbException("Exclusão falhou: ID não encontrado!");
			} else {
				System.out.println("Vendedor deletado com sucesso! ID = " + id);
			}
		} catch (SQLException e) {
			throw new DbException("Erro ao excluir vendedor: " + e.getMessage());
		}
	}

	// ==========================
	// FIND BY ID
	// ==========================
	@Override
	public Seller findById(Integer id) {
		String sql = "SELECT seller.*, department.Name AS DepName " + "FROM seller INNER JOIN department "
				+ "ON seller.DepartmentId = department.Id " + "WHERE seller.Id = ?";

		try (PreparedStatement st = conn.prepareStatement(sql)) {
			st.setInt(1, id);

			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					Department dep = instantiateDepartment(rs);
					return instantiateSeller(rs, dep);
				}
				return null;
			}
		} catch (SQLException e) {
			throw new DbException("Erro ao buscar vendedor por ID: " + e.getMessage());
		}
	}

	// ==========================
	// FIND BY DEPARTMENT
	// ==========================
	@Override
	public List<Seller> findByDepartment(Department department) {
		String sql = "SELECT seller.*, department.Name AS DepName " + "FROM seller INNER JOIN department "
				+ "ON seller.DepartmentId = department.Id " + "WHERE DepartmentId = ? " + "ORDER BY Name";

		try (PreparedStatement st = conn.prepareStatement(sql)) {
			st.setInt(1, department.getId());

			try (ResultSet rs = st.executeQuery()) {
				List<Seller> list = new ArrayList<>();
				Map<Integer, Department> map = new HashMap<>();

				while (rs.next()) {
					Department dep = map.computeIfAbsent(rs.getInt("DepartmentId"), id -> {
						try {
							return instantiateDepartment(rs);
						} catch (SQLException e) {
							throw new RuntimeException(e);
						}
					});

					list.add(instantiateSeller(rs, dep));
				}
				return list;
			}
		} catch (SQLException e) {
			throw new DbException("Erro ao buscar vendedores por departamento: " + e.getMessage());
		}
	}

	// ==========================
	// FIND ALL
	// ==========================
	@Override
	public List<Seller> findAll() {
		String sql = "SELECT seller.*, department.Name AS DepName " + "FROM seller INNER JOIN department "
				+ "ON seller.DepartmentId = department.Id " + "ORDER BY Name";

		try (PreparedStatement st = conn.prepareStatement(sql); ResultSet rs = st.executeQuery()) {

			List<Seller> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();

			while (rs.next()) {
				Department dep = map.computeIfAbsent(rs.getInt("DepartmentId"), id -> {
					try {
						return instantiateDepartment(rs);
					} catch (SQLException e) {
						throw new RuntimeException(e);
					}
				});

				list.add(instantiateSeller(rs, dep));
			}
			return list;

		} catch (SQLException e) {
			throw new DbException("Erro ao buscar todos os vendedores: " + e.getMessage());
		}
	}

	// ==========================
	// AUXILIARES Para Instanciar Objetos

	// ==========================
	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller obj = new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setBirthDate(rs.getDate("BirthDate").toLocalDate());
		obj.setDepartment(dep);
		return obj;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Seller> findByDepartment(Integer departmentId) {
		// TODO Auto-generated method stub
		return null;
	}
}
