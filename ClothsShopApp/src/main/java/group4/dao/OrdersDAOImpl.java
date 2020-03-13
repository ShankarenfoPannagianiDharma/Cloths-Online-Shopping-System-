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
import group4.model.Product;

@Repository
public class OrdersDAOImpl implements OrdersDAO 
{

	static NamedParameterJdbcTemplate namedParameterJdbcTemplate; //IMPORTANT

	@Autowired	//MAKE CONNECTION AUTOMATICALLY W/DBASE
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		ProductsDAOImpl.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	//utility function to map sql row =to=> object class
	private static final class OrdersMapper implements RowMapper<Order> 
	{
		public Order mapRow(ResultSet rs, int rowNum) throws SQLException 
		{
			Order order = new Order();
			order.setOrderID(rs.getInt("id"));
			order.setCustomerID(rs.getInt("customerid"));
			order.setDestinationAddress(rs.getString("destination"));
			
			//get products in order->calls for another sql query for each order
			order.setPurchasedItems(getItemsOrdered(order.getOrderID()));
			
			return order;
		}
	}
	//utility function to map sql row =to=> object class
	//NOT THE SAME AS THE MAPPER IN PRODUCTSDAOIMPL!
	private static final class PurchasedProductsMapper implements RowMapper<Product> 
	{
		//resultset must INNER JOIN with itemtypes and itemsizes
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException 
		{
			Product item = new Product();
			item.setProductID(rs.getInt("id"));
			item.setProductName(rs.getString("name"));
			item.setImgURL(rs.getString("image"));
			item.setPrice(rs.getDouble("price"));
			item.setProductSize( rs.getString("size") );
			item.setType( rs.getString("category") );
			item.setStock( rs.getInt("amount") );
			return item;
		}
	}
	
	public static List<Product> getItemsOrdered(int orderID)
	{
		//get the products that are ordered
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", orderID);

		//consults orderdetails table -<- products -|- itemsizes & itemtype
		String sql = "SELECT products.id,name,orderdetails.price,image,itemSizes.size,category,amount FROM orderdetails "
				+ "WHERE  products.id=:id "
				+ "INNER JOIN products ON products.id = orderdetails.itemid "
				+ "INNER JOIN itemSizes ON itemSizes.id = products.size "
				+ "INNER JOIN itemTypes ON itemTypes.id = products.type ";
		
		List<Product> results = namedParameterJdbcTemplate.query(sql, params, new PurchasedProductsMapper());

		if(results.size() == 0) 
		{ return null; }
		return results;
	}
	
	@Override
	public List<Order> getCustomersOrders(String customerID) 
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", customerID);

		String sql = "SELECT * FROM orders "
				+ "WHERE customerid=:id ";

		List<Order> results = namedParameterJdbcTemplate.query(sql, params, new OrdersMapper());

		if(results.size() == 0) 
		{ return null; }
		
		return results;
	}

	@Override
	public List<Order> getAllOrders() 
	{
		Map<String, Object> params = new HashMap<String, Object>();

		String sql = "SELECT * FROM orders ";

		List<Order> results = namedParameterJdbcTemplate.query(sql, params, new OrdersMapper());

		if(results.size() == 0) 
		{ return null; }
		
		return results;
	}

}
