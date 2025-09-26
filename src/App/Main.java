package App;

// Importa a classe LocalDate para representar datas (nascimento do vendedor)
import java.time.LocalDate;

// Importa as classes Department e Seller do pacote model.entities
import model.entities.Department;
import model.entities.Seller;

public class Main {

    public static void main(String[] args) {
        
        // -----------------------------------------------------
        // 1. Criando um objeto Department
        // -----------------------------------------------------
        // O construtor do Department recebe (id, nome)
        Department department = new Department(1, "Pens");
        
        // Exibe o departamento criado (usa o método toString da classe Department)
        System.out.println("Departamento criado: " + department);
        
        
        // -----------------------------------------------------
        // 2. Criando um objeto Seller (vendedor)
        // -----------------------------------------------------
        // O construtor do Seller recebe:
        // (id, nome, email, dataNascimento, salarioBase, departamento)
        Seller seller = new Seller(
                1,                          // Identificador único do vendedor
                "Bob",                      // Nome
                "bob@gmail.com",            // Email
                LocalDate.of(1998, 9, 21),  // Data de nascimento (21/09/1998)
                3000.0,                     // Salário base
                department                  // Departamento associado
        );
        
        
        // -----------------------------------------------------
        // 3. Exibindo informações detalhadas do vendedor
        // -----------------------------------------------------
        // Aqui usamos os getters para acessar os dados individuais.
        // Isso é útil quando queremos mostrar informações específicas
        // de forma organizada.
        System.out.println("\n--- Detalhes do Vendedor ---");
        System.out.println("ID: " + seller.getId());
        System.out.println("Nome: " + seller.getName());
        System.out.println("Email: " + seller.getEmail());
        System.out.println("Data de Nascimento: " + seller.getBirthDate());
        System.out.println("Salário Base: " + seller.getBaseSalary());
        System.out.println("Departamento: " + seller.getDepartment().getName());

        
        // -----------------------------------------------------
        // 4. Exibindo o objeto completo
        // -----------------------------------------------------
        // Aqui chamamos diretamente o objeto "seller".
        // O Java automaticamente usa o método toString()
        // da classe Seller para imprimir todos os dados de forma resumida.
        System.out.println("\nRepresentação completa do objeto: " + seller);
    }
}
