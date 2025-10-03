package model.dao.Implements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection conn; // Conexão com o banco de dados

    public DepartmentDaoJDBC(Connection conn) { // Construtor que recebe a conexão
        this.conn = conn;
    }

    // Implementação do método insert para inserir um novo departamento
    @Override
    public void insert(Department obj) {
        PreparedStatement st = null; // Declaração preparada para a consulta SQL
        try { 
            st = conn.prepareStatement( 
                "INSERT INTO department (Name) VALUES (?)", // Consulta SQL com parâmetro
                Statement.RETURN_GENERATED_KEYS
            );

            st.setString(1, obj.getName()); // Define o valor do parâmetro

            int rowsAffected = st.executeUpdate(); // Executa a consulta e obtém o número de linhas afetadas

            if (rowsAffected > 0) { // Se pelo menos uma linha foi afetada
                ResultSet rs = st.getGeneratedKeys(); // Obtém as chaves geradas (IDs)
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("Erro inesperado: nenhuma linha inserida!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    // Implementação do método update para atualizar um departamento existente
    @Override
    public void update(Department obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                "UPDATE department SET Name = ? WHERE Id = ?"
            );

            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    // Implementação do método deleteById para deletar um departamento pelo ID
    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM department WHERE Id = ?");

            st.setInt(1, id);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    // Implementação do método findById para buscar um departamento pelo ID
    @Override
    public Department findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                "SELECT * FROM department WHERE Id = ?"
            );
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Department obj = instantiateDepartment(rs);
                return obj;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    // Implementação do método findAll para buscar todos os departamentos
    @Override
    public List<Department> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                "SELECT * FROM department ORDER BY Name"
            );
            rs = st.executeQuery();

            List<Department> list = new ArrayList<>();

            while (rs.next()) {
                Department obj = instantiateDepartment(rs);
                list.add(obj);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    // Método auxiliar para instanciar um objeto Department a partir do ResultSet-
    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department obj = new Department();
        obj.setId(rs.getInt("Id"));
        obj.setName(rs.getString("Name"));
        return obj;
    }
}
