package group4.model;

import java.util.List;

public class Order 
{
	private int orderID;
	private int customerID;
	private String destinationAddress;
	private List<OrderDetail> purchasedItems;
	
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
	public List<OrderDetail> getPurchasedItems() {
		return purchasedItems;
	}
	public void setPurchasedItems(List<OrderDetail> purchasedItems) {
		this.purchasedItems = purchasedItems;
	}
	
	//calculate the total cost of all products
	public double orderTotalCosts()
	{
		double calculated = 0;
		if(purchasedItems != null)
		{
			for(OrderDetail item : purchasedItems)
			{ calculated += item.getPrice(); }
		}
		return calculated;
	}
	
	//calculate the total number of items in list
	public int orderTotalNum()
	{
		int calculated = 0;
		if(purchasedItems != null)
		{
			for(OrderDetail item : purchasedItems)
			{ calculated += item.getAmount(); }
		}
		return calculated;
	}
}
