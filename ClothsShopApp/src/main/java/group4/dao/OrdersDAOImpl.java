package group4.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import group4.model.Order;
import group4.model.OrderDetail;
import group4.model.Product;

@Repository
public class OrdersDAOImpl implements OrdersDAO {

	static NamedParameterJdbcTemplate namedParameterJdbcTemplate; // IMPORTANT

	@Autowired // MAKE CONNECTION AUTOMATICALLY W/DBASE
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		OrdersDAOImpl.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	// utility function to map sql row =to=> object class
	private static final class OrdersMapper implements RowMapper<Order> {
		public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
			Order order = new Order();
			order.setOrderID(rs.getInt("id"));
			order.setCustomerID(rs.getInt("customerid"));
			order.setDestinationAddress(rs.getString("destination"));

			// get products in order->calls for another sql query for each order
			order.setPurchasedItems(getOrderDetail(order.getOrderID()));

			return order;
		}
	}

	// utility function to map sql row =to=> object class
	private static final class OrderDetailsMapper implements RowMapper<OrderDetail> {
		public OrderDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
			OrderDetail order = new OrderDetail();
			order.setOrderid(rs.getInt("orderid"));
			order.setAmount(rs.getInt("amount"));
			order.setItemid(rs.getInt("itemid"));
			order.setItemName(rs.getString("name"));
			order.setPrice(rs.getDouble("price"));

			return order;
		}
	}
	
	//Mapper specifically for stock
	private static final class stockMapper implements RowMapper<Product>{
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			Product item = new Product();
			item.setStock(rs.getInt("stock"));
			return item;
		}
	}

	//extension of get orders- get items that are ordered n that cart
	public static List<OrderDetail> getOrderDetail(int orderID) {
		// get the products that are ordered
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderID", orderID);

		// consults orderdetails table -<- products -|- itemsizes & itemtype
		String sql = "SELECT orderid,itemid,amount,orderdetails.price,name FROM orderdetails INNER JOIN products ON orderdetails.itemid = products.id WHERE orderid=:orderID";

		List<OrderDetail> results = namedParameterJdbcTemplate.query(sql, params, new OrderDetailsMapper());

		if (results.size() == 0) 
		{ return null; }
		return results;
	}

	@Override
	public List<Order> getCustomersOrders(int customerID) 
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", customerID);

		String sql = "SELECT * FROM orders WHERE customerid=:id ";
		List<Order> results = namedParameterJdbcTemplate.query(sql, params, new OrdersMapper());

		if (results.size() == 0) 
		{ return null; }
		
		return results;
	}

	@Override
	public List<Order> getAllOrders() {
		Map<String, Object> params = new HashMap<String, Object>();

		String sql = "SELECT * FROM orders ";

		List<Order> results = namedParameterJdbcTemplate.query(sql, params, new OrdersMapper());

		if (results.size() == 0) {
			return null;
		}

		return results;
	}

	//check if item amount can be satisfied by stock
	@Override
	public boolean hasEnoughStock(OrderDetail item) 
	{
		//get product of item
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", item.getItemid());
		String sql = "SELECT stock FROM products WHERE products.id=:id ";
		List<Product> results = namedParameterJdbcTemplate.query(sql, params, new stockMapper());

		if (results.size() != 1) 
		{ return false; }

		Product product = results.get(0);

		//compare values
		return item.getAmount() <= product.getStock();
	}

	@Override
	public void createOrder(int id, List<OrderDetail> cart,String addr) 
	{
		//create new order for customer id
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", id);
		params.put("addressTarget",addr);
		String sql = "INSERT INTO ORDERS (customerid,destination) VALUES (:userID,:addressTarget)";
		namedParameterJdbcTemplate.update(sql, params);
		
		//retrieve the newly created order's id: get customerid's last created order
		List<Order> customerOrders = getCustomersOrders(id);
		Order target = customerOrders.get(customerOrders.size()-1);
		
		//create orderDetails for order
		for(OrderDetail item : cart)
		{
			Map<String, Object> params2 = new HashMap<String, Object>();
			params2.put("orderID", target.getOrderID());
			params2.put("itemID", item.getItemid());
			params2.put("amount", item.getAmount());
			params2.put("price", item.getPrice());
			sql = "INSERT INTO orderdetails (orderid, itemid, amount, price) VALUES (:orderID, :itemID, :amount, :price)";
			namedParameterJdbcTemplate.update(sql, params2);
			
			//modify the stock in products
			//calculate new values
			sql = "SELECT stock FROM products WHERE products.id=:itemID ";
			List<Product> results = namedParameterJdbcTemplate.query(sql, params2, new stockMapper());			
			Product product = results.get(0);
			
			Map<String, Object> params3 = new HashMap<String, Object>();
			params3.put("id", item.getItemid());
			params3.put("newstock", product.getStock() - item.getAmount());
			sql = "UPDATE products SET stock=:newstock WHERE products.id=:id ";
			namedParameterJdbcTemplate.update(sql, params3);
		}
		
	}

	//specifically get one specific order info
	@Override
	public Order getSpecificOrder(int orderID) 
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", orderID);
		String sql = "SELECT * FROM orders WHERE id=:id";

		List<Order> results = namedParameterJdbcTemplate.query(sql, params, new OrdersMapper());

		if (results.size() != 1) 
		{return null;}

		return results.get(0);
	}
}
