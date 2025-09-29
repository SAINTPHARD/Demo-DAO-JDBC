package model.dao;

import java.util.List;

import model.entities.Department;

public interface DepartmentDao {
	// Interface para operações de acesso a dados do departamento
	void insert(Department objeto); // Insere um novo departamento
	void update(Department objeto);
	void deleteById(Integer id);
	Department findById(Integer id); // Busca departamento por ID
	//java.util.List<Department> findAll(); // Busca todos os departamentos ou 
	List<Department> findAll(); // Busca todos os departamentos

}

