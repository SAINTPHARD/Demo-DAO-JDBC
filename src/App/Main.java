package App;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
// IMPORTANTE: Você deve importar a classe Seller!
import model.entities.Seller; // <--- Não se esqueça deste import! 

public class Main {

    public static void main(String[] args) {
    	
    	SellerDao sellerDao = DaoFactory.createSellerDao(); // Cria o SellerDao usando a fábrica
    	
    	// Teste 1: Buscar um vendedor por ID
    	System.out.println("\n--- TEST 1: seller findById ---");
    	Seller seller = sellerDao.findById(3); // Busca o vendedor com ID 3 no banco de dados
    	System.out.println(seller); // Exibe os detalhes do vendedor encontrado
    	
    	// Teste 2: Buscar vendedores por departamento
    	System.out.println("\n--- TEST 2: seller findByDepartment ---");
    	Department department = new Department(2, null); // Cria um departamento com ID 2
    	List<Seller> list = sellerDao.findByDepartment(department); // Busca vendedores deste departamento
    	
    	for (Seller obj : list) { 
			System.out.println(obj); // Exibe cada vendedor encontrado
		}
    	
    	
		// Teste 3: Buscar todos os vendedores
		System.out.println("\n--- Teste 3: seller findAll ---");
		List<Seller> list2 = sellerDao.findAll(); // Busca todos os vendedores no banco de dados
		// lógica para exibir a lista de todos os vendedores
		for (Seller obj : list2) {
			System.out.println(obj); // Exibe cada vendedor encontrado
		}
		
    }
}