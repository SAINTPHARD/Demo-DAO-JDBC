package model.dao;

import db.DB;
import model.dao.Implements.SellerDaoJDBC;

public class DaoFactory {

	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC(DB.getConnection()); // Passa a conexão obtida do DB para o construtor do SellerDaoJDBC
		//return new model.dao.Implements.SellerDaoJDBC(); // Retorna uma nova instância de SellerDaoJDBC
		
	}
}
