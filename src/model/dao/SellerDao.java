package model.dao;

import java.util.List;

import model.entities.Seller;

public interface SellerDao {

	void insert(Seller objeto); // Insere um novo vendedor
	void update(Seller objeto);
	void deleteById(Integer id); // Deleta vendedor por ID
	Seller findById(Integer id); // Busca vendedor por ID
	List<Seller> findAll(); // Busca todos os vendedores
}
