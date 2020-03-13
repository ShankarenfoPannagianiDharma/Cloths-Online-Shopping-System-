package group4.dao;

import java.util.List;

import group4.model.Order;

public interface OrdersDAO 
{
	//get all orders from one customer
	List<Order> getCustomersOrders(String customerID);
	
	//get all orders
	List<Order> getAllOrders();

}
