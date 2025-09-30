package model.dao;

import java.util.List;

import model.entities.Department;
import model.entities.Seller;

public interface SellerDao {

	// Interface para operações de acesso a dados do vendedor
	void insert(Seller objeto); // Insere um novo vendedor
	void update(Seller objeto);
	void deleteById(Integer id); // Deleta vendedor por ID
	Seller findById(Integer id); // Busca vendedor por ID
	List<Seller> findAll(); // Busca todos os vendedores
	List<Seller> findByDepartment(Department department); // Busca vendedores por departamento
	List<Seller> findByDepartment(Integer departmentId);
}

/**
 * Busca uma lista de vendedores (Seller) dado um objeto Department.
 * @param department O objeto Department contendo o ID para busca.
 * @return Uma lista de Sellers associados a este departamento.
 */