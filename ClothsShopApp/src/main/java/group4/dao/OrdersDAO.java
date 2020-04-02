package group4.dao;

import java.util.List;

import group4.model.Order;
import group4.model.OrderDetail;

public interface OrdersDAO {
	List<Order> getCustomersOrders(int customerID);

	List<Order> getAllOrders();

	boolean hasEnoughStock(OrderDetail item);

	void createOrder(int id, List<OrderDetail> cart, String addr);

	Order getSpecificOrder(int orderID);

}
