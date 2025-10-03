package App;

import java.time.LocalDate;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Main2 {

    public static void main(String[] args) {
        
        // -----------------------------------------------------
        // 1. Criando e exibindo um Department
        // -----------------------------------------------------
        Department department = new Department(1, "Pens");
        System.out.println("Departamento criado: " + department);

        
        // -----------------------------------------------------
        // 2. Criando um objeto Seller local (apenas em memória)
        // -----------------------------------------------------
        Seller sellerLocal = new Seller(
                1, 
                "Bob", 
                "bob@gmail.com", 
                LocalDate.of(1998, 9, 21), 
                3000.0, 
                department
        );

        
        // -----------------------------------------------------
        // 3. Testando SellerDao (Banco de Dados)
        // -----------------------------------------------------
        SellerDao sellerDao = DaoFactory.createSellerDao();
        
        System.out.println("\n=== TESTE 1: Seller findById ===");
        Seller sellerDB = sellerDao.findById(3); 
        
        if (sellerDB != null) {
            System.out.println("Vendedor encontrado no banco:");
            System.out.printf(
                "Seller [id=%d, name=%s, email=%s, birthDate=%s, baseSalary=%.2f, department=%s]%n",
                sellerDB.getId(),
                sellerDB.getName(),
                sellerDB.getEmail(),
                sellerDB.getBirthDate(),
                sellerDB.getBaseSalary(),
                sellerDB.getDepartment()
            );
        } else {
            System.out.println("Nenhum vendedor encontrado com ID = 3");
        }

        
        // -----------------------------------------------------
        // 4. Exibindo informações detalhadas do vendedor local
        // -----------------------------------------------------
        System.out.println("\n=== TESTE 2: Detalhes do Vendedor Local ===");
        System.out.printf(
            "ID: %d%nNome: %s%nEmail: %s%nData de Nascimento: %s%nSalário Base: %.2f%nDepartamento: %s%n",
            sellerLocal.getId(),
            sellerLocal.getName(),
            sellerLocal.getEmail(),
            sellerLocal.getBirthDate(),
            sellerLocal.getBaseSalary(),
            sellerLocal.getDepartment().getName()
        );

        
        // -----------------------------------------------------
        // 5. Exibindo representação completa (toString)
        // -----------------------------------------------------
        System.out.println("\n=== TESTE 3: Representação do Objeto Local ===");
        System.out.println(sellerLocal);

        
        // -----------------------------------------------------
        // 6. Testando CRUD do DepartmentDao
        // -----------------------------------------------------
        DepartmentDao depDao = DaoFactory.createDepartmentDao();
        
        System.out.println("\n=== TESTE 4: DepartmentDao - Inserir ===");
        Department newDepartment = new Department(null, "Music");
        depDao.insert(newDepartment);
        System.out.println("Inserido! Novo ID = " + newDepartment.getId());

        System.out.println("\n=== TESTE 5: DepartmentDao - Atualizar ===");
        Department dep = depDao.findById(newDepartment.getId());
        dep.setName("Instruments");
        depDao.update(dep);
        System.out.println("Atualização concluída!");

        System.out.println("\n=== TESTE 6: DepartmentDao - Buscar Todos ===");
        List<Department> allDeps = depDao.findAll();
        allDeps.forEach(System.out::println);

        System.out.println("\n=== TESTE 7: DepartmentDao - Deletar ===");
        depDao.deleteById(newDepartment.getId());
        System.out.println("Departamento deletado!");
    }
}
