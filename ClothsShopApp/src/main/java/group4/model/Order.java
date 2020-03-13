package group4.model;

import java.util.List;

public class Order 
{
	private int orderID;
	private int customerID;
	private String destinationAddress;
	private List<Product> purchasedItems; //<-- amount causes duplication
	
	//GETTERSETTERS
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customer) {
		this.customerID = customer;
	}
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public String getDestinationAddress() {
		return destinationAddress;
	}
	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}
	public List<Product> getPurchasedItems() {
		return purchasedItems;
	}
	public void setPurchasedItems(List<Product> purchasedItems) {
		this.purchasedItems = purchasedItems;
	}
	
	//calculate the total cost of all products
	public double orderTotalCosts()
	{
		double calculated = 0;
		for(Product item : purchasedItems)
		{ calculated += item.getPrice(); }
		return calculated;
	}
}
