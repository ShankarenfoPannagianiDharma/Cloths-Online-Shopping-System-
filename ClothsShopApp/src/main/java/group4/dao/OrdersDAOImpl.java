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

import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;

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
			order.setPurchasedItems(getItemsOrdered(order.getOrderID()));

			return order;
		}
	}

	// utility function to map sql row =to=> object class
	private static final class OrderDetailsMapper implements RowMapper<OrderDetail> {
		public OrderDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
			OrderDetail order = new OrderDetail();
			order.setId(rs.getInt("id"));
			order.setAmount(rs.getInt("amount"));
			order.setItemid(rs.getInt("itemid"));
			order.setOrderid(rs.getInt("orderid"));

			return order;
		}
	}

	// utility function to map sql row =to=> object class
	// NOT THE SAME AS THE MAPPER IN PRODUCTSDAOIMPL!
	private static final class PurchasedProductsMapper implements RowMapper<Product> {
		// resultset must INNER JOIN with itemtypes and itemsizes
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			Product item = new Product();
			item.setProductID(rs.getInt("id"));
			item.setProductName(rs.getString("name"));
			item.setImgURL(rs.getString("image"));
			item.setPrice(rs.getDouble("price"));
			item.setProductSize(rs.getString("size"));
			item.setProductType(rs.getString("category"));
			item.setStock(rs.getInt("amount"));
			return item;
		}
	}

	public static List<Product> getItemsOrdered(int orderID) {
		// get the products that are ordered
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", orderID);

		// consults orderdetails table -<- products -|- itemsizes & itemtype
		String sql = "SELECT products.id,name,orderdetails.price,image,itemSizes.size,category,amount FROM products "
				+ "INNER JOIN orderdetails ON products.id = orderdetails.itemid "
				+ "INNER JOIN orders ON orderdetails.orderid = orders.id "
				+ "INNER JOIN itemSizes ON itemSizes.id = products.size "
				+ "INNER JOIN itemTypes ON itemTypes.id = products.type " + " WHERE  orders.id=:id ";

		List<Product> results = namedParameterJdbcTemplate.query(sql, params, new PurchasedProductsMapper());

		if (results.size() == 0) {
			return null;
		}
		return results;
	}

	@Override
	public List<OrderDetail> getOrderDetail(int orderID, int userId) {
		// get the products that are ordered
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderID", orderID);
		params.put("userId", userId);

		// consults orderdetails table -<- products -|- itemsizes & itemtype
		String sql = "SELECT * FROM orderdetails " + "INNER JOIN products ON products.id = orderdetails.itemid "
				+ "INNER JOIN orders ON orderdetails.orderid = orders.id "
				+ " WHERE  orders.id=:orderID AND orders.customerid=:userId";

		List<OrderDetail> results = namedParameterJdbcTemplate.query(sql, params, new OrderDetailsMapper());
		System.out.println("getOrderDetail");
		System.out.println(params);
		System.out.println("result is" + results);
		if (results.size() == 0) {
			return null;
		}
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addCartItem(int orderID, int productID, int userId) {
		// get the products that are ordered
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderID", orderID);
		params.put("productID", productID);
		params.put("userId", userId);

		// consults orderdetails table -<- products -|- itemsizes & itemtype
		String sql = "SELECT * FROM orderdetails "
				+ "INNER JOIN products ON products.id = orderdetails.itemid "
				+ "INNER JOIN orders ON orderdetails.orderid = orders.id "
				+ " WHERE  orders.id=:orderID AND products.id=:productID AND orders.customerid=:userId ";

		List<OrderDetail> results = namedParameterJdbcTemplate.query(sql, params, new OrderDetailsMapper());

		if (results.size() > 0) {
			System.out.println("update");

			System.out.println(params);
			sql = "UPDATE orderdetails SET amount = amount + 1 "
					+ " WHERE  orderdetails.itemid=:productID AND orderdetails.orderid=:orderID ";

		} else {
			System.out.println("insert");
			
			System.out.println(params);

			sql = "INSERT INTO orderdetails (itemid, orderid, price) VALUES (:productID, :orderID, 0)";
		}

		namedParameterJdbcTemplate.update(sql, params);

	}

	@Override
	public List<Order> getCustomersOrders(int customerID) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", customerID);

		String sql = "SELECT * FROM orders " + "WHERE customerid=:id ";

		List<Order> results = namedParameterJdbcTemplate.query(sql, params, new OrdersMapper());

		if (results.size() == 0) {
			return null;
		}

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
	
	//#Bao
	public void createNewOrder(int userId) {
		//get the userID 
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userId);
		String sql = "INSERT INTO ORDERS (customerid,destination) "
					+ "VALUES (:userID,(SELECT destination FROM orders WHERE customerid=:userID ORDER BY orders.id DESC LIMIT 1))";
		
		namedParameterJdbcTemplate.update(sql, params);
	}
}
