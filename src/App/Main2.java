package App;

import java.time.LocalDate;
import db.DbException; // Import necessário para o tratamento de exceção
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Main2 {

    public static void main(String[] args) {
        
        // --- 1. Preparação dos Objetos em Memória ---
        
        // Cria um objeto Department.
        // IMPORTANTE: Este departamento (com Id=1) já deve existir na sua tabela 'department'.
        Department department = new Department(1, "Computers"); // Usando "Computers" como exemplo
        
        // Cria um novo objeto Seller para ser inserido no banco.
        // CORRIGIDO: O Id para um novo vendedor deve ser 'null', pois o banco de dados irá gerá-lo.
        Seller seller = new Seller(
                null,                       // Id é nulo para inserção
                "Bob",                      // Nome
                "bob@gmail.com",            // Email
                LocalDate.of(1998, 9, 21),  // Data de nascimento
                3000.0,                     // Salário base
                department                  // Departamento associado
        );
        
        // --- 2. Interação com o Banco de Dados (dentro de um try-catch) ---
        
        // CORRIGIDO: Operações de banco de dados devem estar dentro de um bloco try-catch.
        try {
            // Cria a implementação do DAO através da fábrica.
            SellerDao sellerDao = DaoFactory.createSellerDao();
            
            System.out.println("=== TESTE: Inserindo um novo vendedor ===");
            
            // Tenta inserir o novo vendedor no banco de dados.
            sellerDao.insert(seller);
            
            // Se a inserção funcionar, o objeto 'seller' agora terá o ID gerado pelo banco.
            System.out.println("Inserção concluída! Novo ID gerado = " + seller.getId());

        } 
        catch (DbException e) {
            // Captura qualquer erro de banco de dados que possa ter ocorrido.
            System.err.println("Erro ao inserir vendedor: " + e.getMessage());
            e.printStackTrace(); // Útil para depuração
        }
        
        // --- 3. Exibindo informações do objeto Seller em memória ---
        
        System.out.println("\n--- Detalhes do Objeto Seller ---");
        // CORRIGIDO: Usando a variável correta 'seller' em todas as chamadas.
        System.out.println("ID: " + seller.getId());
        System.out.println("Nome: " + seller.getName());
        System.out.println("Email: " + seller.getEmail());
        System.out.println("Data de Nascimento: " + seller.getBirthDate());
        System.out.println("Salário Base: " + seller.getBaseSalary());
        System.out.println("Departamento: " + seller.getDepartment().getName());
    }
}