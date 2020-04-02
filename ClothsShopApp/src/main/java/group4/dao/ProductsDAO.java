package group4.dao;

import java.util.List;

import group4.model.Product;

public interface ProductsDAO 
{
	
	List<Product> getAllProducts();
	
	List<Product> getProductsOfType(String type);
	
	List<Product> getProductsOfSize(String size);

	Product getProductFromId(int targetID);
	
	List<Product> getProductsOutOfStock();

	boolean hasStock(int productId);

	double getProductCost(int productId);

	String getProductName(int productId);
}
