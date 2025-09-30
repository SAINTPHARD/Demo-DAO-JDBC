package model.dao.Implements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList; 
import java.util.HashMap; // Importação adicionada para usar HashMap
import java.util.List;
import java.util.Map; // Importação adicionada para usar Map

import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;
import db.DB;
import db.DbException;

public class SellerDaoJDBC implements SellerDao {
	
	private Connection conn; 
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller objeto) {
		// Implementação pendente
	}

	@Override
	public void update(Seller objeto) {
		// Implementação pendente
	}

	@Override
	public void deleteById(Integer id) {
		// Implementação pendente
	}

	@Override
	public Seller findById(Integer id) {
		// Apenas o PreparedStatement é gerenciado no try-with-resources
		try (PreparedStatement st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE seller.Id = ?")) {
			
			// 1. SETA o parâmetro (ANTES de executar)
			st.setInt(1, id);
			
			// 2. EXECUTA e gerencia o ResultSet
			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					Department dep = instantiateDepartment(rs);
					Seller obj = instantiateSeller(rs, dep);
					return obj;
				}
				return null;
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
    
	@Override
	public List<Seller> findByDepartment(Department department) { 
		
		try (PreparedStatement st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ? " 
					+ "ORDER BY Name")) {
			
			st.setInt(1, department.getId()); // Seta o parâmetro
			
			try (ResultSet rs = st.executeQuery()) { // Executa
				
				List<Seller> list = new ArrayList<>();
				// Mapa para armazenar departamentos e evitar instanciar duplicatas (otimização de memória)
				Map<Integer, Department> map = new HashMap<>(); 
				
				while (rs.next()) { 
					int depId = rs.getInt("DepartmentId");
					
					// 1. Reutiliza Department se já estiver no mapa
					Department dep = map.get(depId);
					
					if (dep == null) {
						// 2. Se não está no mapa, instancia e armazena
						dep = instantiateDepartment(rs);
						map.put(depId, dep);
					}
					
					Seller obj = instantiateSeller(rs, dep);
					list.add(obj);
				}
				return list;
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public List<Seller> findAll() {
		// A implementação do findAll DEVE usar o Map, pois retorna todos os departamentos
		try (PreparedStatement st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "ORDER BY Name")) {
			
			try (ResultSet rs = st.executeQuery()) { // Executa
				List<Seller> list = new ArrayList<>();
				// Mapa para armazenar departamentos e evitar instanciar duplicatas
				Map<Integer, Department> map = new HashMap<>(); 
				
				while (rs.next()) { 
					int depId = rs.getInt("DepartmentId");
					
					// 1. Reutiliza Department se já estiver no mapa
					Department dep = map.get(depId);
					
					if (dep == null) {
						// 2. Se não está no mapa, instancia e armazena
						dep = instantiateDepartment(rs);
						map.put(depId, dep);
					}
					
					Seller obj = instantiateSeller(rs, dep);
					list.add(obj);
				}
				return list;
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

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
