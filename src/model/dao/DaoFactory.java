package model.dao;

import model.dao.Implements.SellerDaoJDBC;

public class DaoFactory {

	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC();
		//return new model.dao.Implements.SellerDaoJDBC();
		
	}
}
