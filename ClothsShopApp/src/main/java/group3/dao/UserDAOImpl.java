package group3.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import group3.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

	NamedParameterJdbcTemplate namedParameterJdbcTemplate; //IMPORTANT

	@Autowired	//MAKE CONNECTION AUTOMATICALLY W/DBASE
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	//returns user of email
	// @Override
	public User findByEmail(String email) 
	{

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("email", email);

		String sql = "SELECT * FROM users WHERE email=:email";

//        Student result = namedParameterJdbcTemplate.queryForObject(
//                    sql,
//                    params,
//                    new StudentMapper());

		List<User> results = namedParameterJdbcTemplate.query(sql, params, new UserMapper());

		if(results.size() == 0) {
			return null;
		}
		User user = results.get(0);
		return user;

	}

	//gets all users from database- returns a list of users
	public List<User> GetAllUsers() 
	{
		String sql = "SELECT * FROM users";
		Map<String, Object> params = new HashMap<String, Object>();

//        Student result = namedParameterJdbcTemplate.queryForObject(
//                    sql,
//                    params,
//                    new StudentMapper());

		List<User> results = namedParameterJdbcTemplate.query(sql, params, new UserMapper());

		if(results.size() == 0) {
			return null;
		};
		return results;

	}
	
	//utility function to map sql row =to=> object class
	private static final class UserMapper implements RowMapper<User> 
	{
		public User mapRow(ResultSet rs, int rowNum) throws SQLException 
		{
			User usr = new User();
			usr.setUserName(rs.getString("name"));
			usr.setEmail(rs.getString("email"));
			usr.setPassword(rs.getString("password"));
			usr.setAdmin(rs.getString("admin"));
			return usr;
		}
	}

	
}
