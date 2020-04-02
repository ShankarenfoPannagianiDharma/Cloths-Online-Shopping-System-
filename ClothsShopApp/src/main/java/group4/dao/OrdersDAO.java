package group4.dao;

import java.util.List;

import group4.model.Order;
import group4.model.OrderDetail;

public interface OrdersDAO {
	// get all orders from one customer
	List<Order> getCustomersOrders(int customerID);

	// get all orders
	List<Order> getAllOrders();

	//TODO: DELETE
	void addCartItem(int orderID, int productID, int userId);

	List<OrderDetail> getOrderDetail(int orderID, int userId);
	
	//TODO: DELETE #Bao
	void createNewOrder(int id);

	boolean hasEnoughStock(OrderDetail item);

	void createOrder(int id, List<OrderDetail> cart, String addr);

}
