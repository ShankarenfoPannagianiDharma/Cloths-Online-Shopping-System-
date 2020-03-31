package group4.model;

public class OrderDetail {
	private int id;
	private int orderid;
	private int itemid;
	private int amount;
	private double price;	//price of product at time of sale
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
	

	//methods for utility
	public void incrementAmount() 
	{amount++;}

}
