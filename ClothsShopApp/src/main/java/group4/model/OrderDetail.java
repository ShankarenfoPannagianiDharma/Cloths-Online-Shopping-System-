package group4.model;

public class OrderDetail 
{
	private int orderid;
	private int itemid;
	private int amount;
	private double price;	//price of product at time of sale
	private String itemName;//optional field for product's name

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public int getItemid() {
		return itemid;
	}

	public void setItemid(int itemid) {
		this.itemid = itemid;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double cost) {
		this.price = cost;
	}
	
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	//methods for utility
	public void incrementAmount() 
	{amount++;}


}
