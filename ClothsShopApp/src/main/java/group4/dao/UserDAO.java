package group4.dao;

import java.util.List;

import group4.model.Product;
import group4.model.User;

 
public interface UserDAO {

	User findByEmail(String email);
	
     //int selectedProductByProductID(String email, int productId);
	
      List<Product> findSelectedProducts(String email);
	
	List<User> GetAllUsers();

	int selectedProductByProductID(String email, int productId);

	//List<Product> findSelectedProducts(String email); 
}
