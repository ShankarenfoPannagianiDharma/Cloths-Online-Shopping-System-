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

import group4.model.Product;

@Repository
public class ProductsDAOImpl implements ProductsDAO {

	static NamedParameterJdbcTemplate namedParameterJdbcTemplate; //IMPORTANT

	@Autowired	//MAKE CONNECTION AUTOMATICALLY W/DBASE
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		ProductsDAOImpl.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	//utility function to map sql row =to=> object class
	private static final class ProductsMapper implements RowMapper<Product> 
	{
		//resultset must INNER JOIN with itemtypes and itemsizes
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException 
		{
			Product item = new Product();
			item.setProductID(rs.getInt("id"));
			item.setProductName(rs.getString("name"));
			item.setDescription(rs.getString("description"));
			item.setImgURL(rs.getString("image"));
			item.setPrice(rs.getDouble("price"));
			item.setProductSize( rs.getString("size") );
			item.setProductType( rs.getString("category") );
			item.setStock(rs.getInt("stock"));
			return item;
		}
	}
	
	@Override
	public Product getProductFromId(int targetID) 
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", targetID);

		String sql = "SELECT products.id,name,price,stock,description,image,itemSizes.size,category FROM products "
				+ "INNER JOIN itemSizes ON itemSizes.id = products.size "
				+ "INNER JOIN itemTypes ON itemTypes.id = products.type "
				+ "WHERE products.id=:id ";

		List<Product> results = namedParameterJdbcTemplate.query(sql, params, new ProductsMapper());

		if(results.size() == 0) 
		{ return null; }
		
		Product product = results.get(0);
		
		return product;
	}

	@Override
	public List<Product> getAllProducts() 
	{
		Map<String, Object> params = new HashMap<String, Object>();

		String sql = "SELECT products.id,name,price,stock,description,image,itemSizes.size,category FROM products "
				+ "INNER JOIN itemSizes ON itemSizes.id = products.size "
				+ "INNER JOIN itemTypes ON itemTypes.id = products.type ";

		List<Product> results = namedParameterJdbcTemplate.query(sql, params, new ProductsMapper());

		if(results.size() == 0) 
		{ return null; }
		
		return results;
	}

	@Override
	public List<Product> getProductsOfType(String type) 
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", type);

		String sql = "SELECT products.id,name,price,stock,description,image,itemSizes.size,category FROM products "
				+ "INNER JOIN itemSizes ON itemSizes.id = products.size "
				+ "INNER JOIN itemTypes ON itemTypes.id = products.type "
				+ "WHERE itemTypes.category = :type ";

		List<Product> results = namedParameterJdbcTemplate.query(sql, params, new ProductsMapper());

		if(results.size() == 0) 
		{ return null; }
		
		return results;
	}

	@Override
	public List<Product> getProductsOfSize(String size) 
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("size", size);

		String sql = "SELECT products.id,name,price,stock,description,image,itemSizes.size,category FROM products "
				+ "INNER JOIN itemSizes ON itemSizes.id = products.size "
				+ "INNER JOIN itemTypes ON itemTypes.id = products.type "
				+ "WHERE itemSizes.size = :size ";

		List<Product> results = namedParameterJdbcTemplate.query(sql, params, new ProductsMapper());

		if(results.size() == 0) 
		{ return null; }
		
		return results;
	}

	@Override
	public List<Product> getProductsOutOfStock() 
	{
		Map<String, Object> params = new HashMap<String, Object>();
		String sql = "SELECT products.id,name,price,stock,description,image,itemSizes.size,category FROM products "
				+ "INNER JOIN itemSizes ON itemSizes.id = products.size "
				+ "INNER JOIN itemTypes ON itemTypes.id = products.type "
				+ "WHERE products.stock<=0 ";

		List<Product> results = namedParameterJdbcTemplate.query(sql, params, new ProductsMapper());

		if(results.size() == 0) 
		{ return null; }
		return results;
	}
	
}
