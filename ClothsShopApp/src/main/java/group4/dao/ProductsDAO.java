package group4.dao;

import java.util.List;

import group4.model.Product;

public interface ProductsDAO 
{
	Product getProductFromId();
	
	List<Product> getAllProducts();
	
	List<Product> getProductsOfType();
	
	List<Product> getProductsOfSize();
	
	//List<Product> getProductsOutOfStock();
}
