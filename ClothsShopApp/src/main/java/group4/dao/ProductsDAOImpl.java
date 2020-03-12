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

import group4.dao.UserDAOImpl.UserMapper;
import group4.model.Product;
import group4.model.User;

@Repository
public class ProductsDAOImpl implements ProductsDAO {

	static NamedParameterJdbcTemplate namedParameterJdbcTemplate; //IMPORTANT

	@Autowired	//MAKE CONNECTION AUTOMATICALLY W/DBASE
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		ProductsDAOImpl.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public Product getProductFromId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductsOfType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductsOfSize() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//utility function to map sql row =to=> object class
	private static final class ProductsMapper implements RowMapper<Product> 
	{
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException 
		{
			Product item = new Product();
			item.setProductName(rs.getString("name"));
			item.setDescription(rs.getString("description"));
			item.setImgURL(rs.getString("image"));
			item.setPrice(rs.getDouble("price"));
			item.setProductSize( getSizeFromInt(rs.getInt("size")) );
			item.setType( rs.getInt("type") );
			item.setStock(rs.getInt("stock"));
			return item;
		}
	}
	
	//utility function to get size from db
	private static String getSizeFromInt(int typeID)
	{
		String sql = "SELECT category FROM itemtypes WHERE id=:id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", typeID);

		String size = namedParameterJdbcTemplate.query(sql, params));
		
		return size;
	}
	
	//utility function to get type from db
}
