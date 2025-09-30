package App;

import model.dao.DaoFactory;
import model.dao.SellerDao;
// IMPORTANTE: Você deve importar a classe Seller!
import model.entities.Seller; // <--- Não se esqueça deste import! 

public class Main {

    public static void main(String[] args) {
    	
    	SellerDao sellerDao = DaoFactory.createSellerDao(); // Cria o SellerDao usando a fábrica
    	
    	// Teste 1: Buscar um vendedor por ID
    	System.out.println("\n--- Teste 1: seller findById ---");
    	Seller seller = sellerDao.findById(3); // Busca o vendedor com ID 3 no banco de dados
    	
    	System.out.println(seller); // Exibe os detalhes do vendedor encontrado
    	
    }
}