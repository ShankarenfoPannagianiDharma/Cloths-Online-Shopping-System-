package group4.model;

import java.util.List;

//This model describes the user

public class User {
	private int id;
	private String email;
	private String userName;
	private String password;
	private int admin;
	private List<Product> selectedProducts;

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Product> getSelectedProducts() {
		return selectedProducts;
	}
	
	public void setSelectedProducts(List<Product> selectedProduct) {
		this.selectedProducts = selectedProduct;
	}
 
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAdmin(String stringInput) {
		this.admin = Integer.parseInt(stringInput);
	}

	public boolean isAdmin() 
	{
		if(admin == 1)
		{return true;}
		return false;
	}

	
}
