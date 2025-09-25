package App;

import java.time.LocalDate;
import model.entities.Department;
import model.entities.Seller;

/**
 * Classe principal para demonstrar a criação e manipulação de objetos de entidade.
 */
public class Main {

    /**
     * Ponto de entrada da aplicação.
     * @param args Argumentos da linha de comando (não utilizados).
     */
    public static void main(String[] args) {
        
        // --- ETAPA 1: CRIAÇÃO DO OBJETO DEPARTMENT ---
        System.out.println("--- Criando objeto Department ---");
        // Instancia um objeto Department com ID 1 e nome "Pens".
        Department department = new Department(1, "Pens");
        // Imprime o objeto Department usando seu método toString().
        System.out.println(department);
        System.out.println("---------------------------------");
        
        System.out.println("\n--- Criando objeto Seller ---");
        // --- ETAPA 2: CRIAÇÃO DO OBJETO SELLER ---
        // Instancia um objeto Seller, associando-o ao objeto Department criado anteriormente.
        Seller seller = new Seller(
                1,                          // id
                "Bob",                      // name
                "bob@gmail.com",            // email
                LocalDate.of(1998, 9, 21),  // birthDate (usando uma data específica)
                3000.0,                     // baseSalary
                department                  // department
        );
        System.out.println("Objeto Seller criado com sucesso.");
        System.out.println("-----------------------------");

        
        // --- ETAPA 3: EXIBIÇÃO EXPLÍCITA DOS DADOS DO SELLER ---
        // Acessando e imprimindo cada atributo do objeto Seller individualmente
        // para demonstrar como os métodos getters funcionam.
        System.out.println("\n--- Detalhes explícitos do Vendedor ---");
        System.out.println("Acessando com s.getId(): " + seller.getId());
        System.out.println("Acessando com s.getName(): " + seller.getName());
        System.out.println("Acessando com s.getEmail(): " + seller.getEmail());
        System.out.println("Acessando com s.getBirthDate(): " + seller.getBirthDate());
        System.out.println("Acessando com s.getBaseSalary(): " + seller.getBaseSalary());
        // Note como acessamos o nome do departamento através do objeto Department aninhado.
        System.out.println("Acessando com s.getDepartment().getName(): " + seller.getDepartment().getName());
        System.out.println("---------------------------------------");
        
        // Imprime o objeto Seller completo usando o método toString() da classe Seller.
        System.out.println("\n--- Impressão do objeto Seller via toString() ---");
        System.out.println(seller);
        System.out.println("---------------------------------------------");
    }
}