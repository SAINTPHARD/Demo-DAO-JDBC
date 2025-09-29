package model.dao.Implements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; // Importação essencial para tratar exceções de JDBC
import java.util.List;

import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;
import db.DB; // Assumindo que você tem uma classe DB para fechar recursos
import db.DbException; // Assumindo que você tem uma exceção personalizada para o banco de dados

public class SellerDaoJDBC implements SellerDao {
	
	private Connection conn; // Conexão com o banco de dados
	
	public SellerDaoJDBC(Connection conn) { // Construtor que recebe a conexão
		this.conn = conn;
	}

	// Esqueleto da Implementação das operações de acesso a dados do vendedor
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
		PreparedStatement st = null; // Declaração do PreparedStatement
		ResultSet rs = null; // Declaração do ResultSet
		
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE seller.Id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if (rs.next()) {
				
				// 1. Instancia o Department
				Department dep = new Department(); 
				dep.setId(rs.getInt("DepartmentId"));
				dep.setName(rs.getString("DepName"));
				
				// 2. Instancia o Seller e associa o Department
				Seller obj = new Seller();
				obj.setId(rs.getInt("Id"));
				obj.setName(rs.getString("Name"));
				obj.setEmail(rs.getString("Email"));
				obj.setBaseSalary(rs.getDouble("BaseSalary"));
				// Certifique-se que o campo BirthDate no banco NÃO é NULL
				obj.setBirthDate(rs.getDate("BirthDate").toLocalDate()); 
				obj.setDepartment(dep);
				
				return obj;
			}
			return null;
		}
		catch (SQLException e) {
			// Tratamento específico para exceções de banco de dados
			throw new DbException(e.getMessage()); 
		}
		finally {
			DB.closeResultSet(rs); // Assumindo método auxiliar DB.closeResultSet(rs) // Fecha o ResultSet
			DB.closeStatement(st); // Assumindo método auxiliar DB.closeStatement(st)
			// A conexão (conn) NÃO é fechada aqui, pois é injetada e pode ser usada em outras operações
		}
	}

	@Override
	public List<Seller> findAll() {
		// Implementação pendente
		return null;
	}
}


// ====== Explicação detalhada do código ======
// 
/**
 * ======================================================
 * DOCUMENTAÇÃO JAVADOC - SellerDaoJDBC
 * ======================================================
 * 1. Importações Necessárias:
 *   - Importa classes essenciais do JDBC para manipulação de banco de dados.
 *   - Importa classes do modelo (Seller, Department) e utilitários de banco de dados (DB, DbException).
 *   
 * 
 * Classe: SellerDaoJDBC
 * Implementação da interface SellerDao utilizando JDBC.
 * 
 * Função: 
 * - Responsável por realizar operações de acesso a dados (DAO) 
 *   da entidade Seller, com interação direta no banco de dados.
 * 
 * Dependência:
 * - Recebe a conexão Connection via construtor (injeção de dependência).
 * - A conexão não é fechada nesta classe, pois pode ser usada 
 *   em outras operações externas.
 * 
 * ======================================================
 * CONSTRUTOR
 * ======================================================
 * SellerDaoJDBC(Connection conn)
 * - Cria a instância do DAO com a conexão fornecida.
 * - Parâmetro: conn → conexão ativa com o banco de dados.
 * 
 * ======================================================
 * MÉTODOS
 * ======================================================
 * void insert(Seller objeto)
 * - Insere um novo vendedor no banco de dados.
 * - Parâmetro: objeto → instância de Seller a ser persistida.
 * - Implementação pendente.
 * 
 * void update(Seller objeto)
 * - Atualiza os dados de um vendedor existente.
 * - Parâmetro: objeto → instância de Seller com dados atualizados.
 * - Implementação pendente.
 * 
 * void deleteById(Integer id)
 * - Remove um vendedor do banco pelo seu ID.
 * - Parâmetro: id → identificador único do vendedor.
 * - Implementação pendente.
 * 
 * Seller findById(Integer id)
 * - Busca um vendedor pelo seu ID.
 * - Realiza JOIN com a tabela Department e retorna também as 
 *   informações do departamento associado.
 * - Parâmetro: id → identificador único do vendedor.
 * - Retorno: objeto Seller preenchido ou null se não encontrado.
 * - Exceção: lança DbException em caso de erro de SQL.
 * 
 * List<Seller> findAll()
 * - Retorna todos os vendedores cadastrados no banco de dados.
 * - Retorno: lista de Seller ou null (implementação pendente).
 * 
 * ======================================================
 * OBSERVAÇÕES
 * ======================================================
 * - O bloco try-catch-finally garante fechamento de recursos 
 *   (ResultSet, Statement).
 * - Exceções SQL são encapsuladas em DbException.
 * - A conexão (conn) não é fechada dentro da classe, pois foi 
 *   injetada e pode ser reutilizada.
 */
