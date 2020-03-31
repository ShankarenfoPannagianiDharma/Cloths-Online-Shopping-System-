package group4.model;

import java.util.List;

//This model describes the user- has a list of products

public class User {
	private int id;
	private String email;
	private String userName;
	private String password;
	private int admin;
	private List<OrderDetail> selectedProducts;	//the items in user's current cart

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<OrderDetail> getSelectedProducts() {
		return selectedProducts;
	}
	
	public void setSelectedProducts(List<OrderDetail> selectedProduct) {
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

	//method to findif an order of product ID is already in cart- returns that orderDetail if found,
	public OrderDetail itemInCart(int productId) 
	{
		//Test cart size
		if(selectedProducts != null && selectedProducts.size() > 0)
		{
			//go through list until item is found.
			for(int i = 0; i < selectedProducts.size(); i++)
			{
				if(selectedProducts.get(i).getItemid() == productId)
				{return selectedProducts.get(i);}
			}
		}
		return null;
	}

	//get the total number of items in cart
	public int getOrderItemCounts() 
	{
		int itemcounts = 0;
		//visit each item in cart, add up the amounts
		for(int i = 0; i < selectedProducts.size(); i++)
		{ itemcounts = itemcounts + selectedProducts.get(i).getAmount(); }
		return itemcounts;
	}

	//get the total cost of carted items
	public double getOrderTotalCosts() 
	{
		double totals = 0;
		//visit each item in cart, item.price * item.amount
		for(int i = 0; i < selectedProducts.size(); i++)
		{ totals = totals + (selectedProducts.get(i).getAmount() * selectedProducts.get(i).getPrice()); }
		return totals;
	}

	
}
