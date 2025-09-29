package App;

// Importa a classe LocalDate para representar datas (nascimento do vendedor)
import java.time.LocalDate;

import model.dao.DaoFactory;
import model.dao.SellerDao;
// Importa as classes Department e Seller do pacote model.entities
import model.entities.Department;
import model.entities.Seller;

public class Main2 {

    public static void main(String[] args) {
        
        // -----------------------------------------------------
        // 1. Criando um objeto Department
        // -----------------------------------------------------
        // O construtor do Department recebe (id, nome)
        Department department = new Department(1, "Pens");
        
        // Exibe o departamento criado (usa o método toString da classe Department)
        System.out.println("Departamento criado: " + department);
        
        
        // -----------------------------------------------------
        // 2. Criando um objeto Seller (vendedor local)
        // -----------------------------------------------------
        // Este objeto 'seller' é criado apenas na memória
        Seller seller = new Seller(
                1,                          // Identificador único do vendedor
                "Bob",                      // Nome
                "bob@gmail.com",            // Email
                LocalDate.of(1998, 9, 21),  // Data de nascimento (21/09/1998)
                3000.0,                     // Salário base
                department                  // Departamento associado
        );
        
        
        // -----------------------------------------------------------------
        // 3. Testando o DAO: Conexão e Busca no Banco de Dados (findById)
        // -----------------------------------------------------------------
        
        // 3.1. Cria o objeto de acesso a dados (DAO)
        SellerDao sellerDao = DaoFactory.createSellerDao();

        // 3.2. Executa a busca no banco de dados
        // A variável que armazena o resultado da busca deve ser do tipo 'Seller'
        Seller sellerBusca = sellerDao.findById(3); 
    	
    	// Exibe o vendedor encontrado no DB
    	System.out.println("\n--- Busca por ID (DB) ---");
    	// System.out.println("Vendedor ID 3 encontrado no Banco de Dados: " + "" + sellerBusca); 
    	
    	// Formatação multilinha usando os getters do objeto sellerBusca e tabs (\t)
    	System.out.println("Seller [id=" + sellerBusca.getId() + ","); 
    	System.out.println(" name=" + sellerBusca.getName() + ","); 
    	System.out.println(" email=" + sellerBusca.getEmail() + ",");
    	System.out.println(" birthDate=" + sellerBusca.getBirthDate() + ","); 
    	System.out.println(" baseSalary=" + sellerBusca.getBaseSalary() + ",");
    	// A última linha contém o Department.toString()
    	System.out.println(" department=" + sellerBusca.getDepartment() + "]]"); 
        
        // -----------------------------------------------------
        // 4. Exibindo informações detalhadas do vendedor local
        // -----------------------------------------------------
        // Aqui usamos o objeto 'seller' (o Bob) criado na memória.
        System.out.println("\n--- Detalhes do Vendedor (Objeto Local) ---");
        System.out.println("ID: " + seller.getId());
        System.out.println("Nome: " + seller.getName());
        System.out.println("Email: " + seller.getEmail());
        System.out.println("Data de Nascimento: " + seller.getBirthDate());
        System.out.println("Salário Base: " + seller.getBaseSalary());
        System.out.println("Departamento: " + seller.getDepartment().getName());

        
        // -----------------------------------------------------
        // 5. Exibindo o objeto completo (usando toString)
        // -----------------------------------------------------
        // Aqui chamamos diretamente o objeto "seller".
        System.out.println("\nRepresentação completa do objeto local: " + seller);
    }
}
