package App;

import model.dao.DaoFactory;
import model.dao.SellerDao;
// IMPORTANTE: Você deve importar a classe Seller!
import model.entities.Seller; // <--- Não se esqueça deste import! 

public class Main {

    public static void main(String[] args) {
    	
    	SellerDao sellerDao = DaoFactory.createSellerDao(); // Cria o SellerDao usando a fábrica
    	
    	// LINHA CORRIGIDA: Agora a variável é do tipo Seller
    	Seller seller = sellerDao.findById(3); // Busca o vendedor com ID 3 no banco de dados
    	
    	System.out.println(seller); // Exibe os detalhes do vendedor encontrado
    	
    }
}