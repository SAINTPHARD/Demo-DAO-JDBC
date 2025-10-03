package App;

import java.time.LocalDate;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller; 

public class Main {

    public static void main(String[] args) {
    	
    	// Cria a fábrica DAO e obtém a instância do SellerDaoJDBC
    	SellerDao sellerDao = DaoFactory.createSellerDao(); 
    	
    	// --- TESTE 1: Consulta por ID ---
    	System.out.println("\n=== TESTE 1: seller findById (Consultar Vendedor por ID) ===");
    	Seller seller = sellerDao.findById(3); 
    	
    	if (seller != null) {
    		System.out.println("Vendedor encontrado:");
    		System.out.println(seller);
    	} else {
    		System.out.println("Vendedor com ID 3 não encontrado.");
    	}
    	
    	// --- TESTE 2: Consulta por Departamento ---
    	System.out.println("\n=== TESTE 2: seller findByDepartment (Consultar Vendedores por Departamento) ===");
    	Department department = new Department(2, null); 
    	List<Seller> list = sellerDao.findByDepartment(department); 
    	
    	if (list != null && !list.isEmpty()) {
    		System.out.println("Vendedores encontrados para o Departamento ID 2:");
    		for (Seller obj : list) {
    			System.out.println(obj); 
    		}
    	} else {
    		System.out.println("Nenhum vendedor encontrado para o Departamento ID 2.");
    	}
    	
    	// --- TESTE 3: Consulta Todos ---
    	System.out.println("\n=== TESTE 3: seller findAll (Consultar Todos os Vendedores) ===");
    	List<Seller> listAll = sellerDao.findAll();
    	
    	if (listAll != null && !listAll.isEmpty()) {
    		System.out.println("Todos os vendedores encontrados:");
    		for (Seller obj : listAll) {
    			System.out.println(obj); 
    		}
    	} else {
    		System.out.println("Nenhum vendedor encontrado.");
    	}
    	
    	// --- TESTE 4: Inserção (Insert) ---
    	System.out.println("\n=== TESTE 4: seller insert (Inserir Novo Vendedor) ===");
    	// Prepara um novo vendedor para inserção
    	Department newDep = new Department(1, null); // Associa ao Departamento ID 1
    	Seller newSeller = new Seller(
    			null, 
    			"Robedson SAINTPHARD", 
    			"robedson-@test.com", 
    			LocalDate.of(2000, 10, 10), 
    			3500.0, 
    			newDep
    	);
    	
    	// Insere o novo vendedor no banco de dados e obtém o ID gerado
    	sellerDao.insert(newSeller);
    	System.out.println("Vendedor inserido! Novo ID: " + newSeller.getId());
    	
    	// Opcional: Busca o vendedor recém-inserido para confirmar a operação
    	System.out.println("Verificação (findById com novo ID):");
    	// Verifica se o ID foi realmente gerado antes de tentar buscar
    	if (newSeller.getId() != null) {
    		Seller insertedSeller = sellerDao.findById(newSeller.getId());
    		System.out.println(insertedSeller);
    	} else {
    		System.out.println("ERRO: O ID do vendedor não foi gerado pelo banco de dados.");
    	}
    	
    	// --- TESTE 5: Atualização (Update) ---
    	System.out.println("\n=== TESTE 5: Atualização de Vendedor (Update) ===");

    	// 1. Busca o vendedor com base no ID para poder atualizá-lo.
    	Seller vendedorParaAtualizar = sellerDao.findById(1);

    	// 2. Verifica se o objeto vendedor foi encontrado antes de prosseguir.
    	if (vendedorParaAtualizar != null) {
    	    System.out.println("Vendedor a ser atualizado: " + vendedorParaAtualizar);
    	    
    	    // 3. Altera os valores dos atributos do objeto em memória.
    	    vendedorParaAtualizar.setName("Martha Joao"); // Define um novo nome.
    	    vendedorParaAtualizar.setEmail("martha.waine@gmail.com"); // Define um novo e-mail.
    	    
    	    // 4. Executa a atualização no banco de dados.
    	    sellerDao.update(vendedorParaAtualizar);
    	    
    	    System.out.println("Atualização concluída com sucesso!");
    	    
    	    // 5. (Opcional) Busca novamente o vendedor para confirmar a alteração.
    	    Seller vendedorAtualizado = sellerDao.findById(1);
    	    System.out.println("Dados confirmados após atualização: " + vendedorAtualizado);
    	    
    	} else {
    	    // Caso o ID não seja encontrado no banco de dados.
    	    System.out.println("Falha na atualização: Vendedor com ID 1 não foi encontrado.");
    	}
    	
    	
    	// --- TESTE 6: Exclusão (Delete) ---
		System.out.println("\n=== TESTE 6: seller deleteById (Excluir Vendedor por ID) ===");
		int idToDelete = 7; // ID do vendedor a ser deletado/Exluido
		sellerDao.deleteById(idToDelete);
		System.out.println("Exclusaõ/Deletar concluída para o ID " + idToDelete + "(se existia).");
		
		// Verifica a deleção tentando buscar o vendedor deletado
		Seller deletedSeller = sellerDao.findById(idToDelete); // declara a variável para verificação de exclusão
		if (deletedSeller == null) {
			System.out.println("Verificação: Vendedor com ID " + idToDelete + " não encontrado (deletado com sucesso).");
		} else {
			System.out.println("Verificação: Vendedor com ID " + idToDelete + " ainda existe: " + deletedSeller);
		}
		
		// --- TESTE 7: Implementação  e Test de departmentDao---
		System.out.println("\n=== TESTE 7: departmentDao (Testando DepartmentDao) ===");
		
		
		// Finaliza o programa
		System.out.println("\n=== FIM DOS TESTES ===");
    }
}
