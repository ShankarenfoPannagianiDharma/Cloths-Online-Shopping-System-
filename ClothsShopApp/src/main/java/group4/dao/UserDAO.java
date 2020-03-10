package group4.dao;

import java.util.List;

import group4.model.User;

 
public interface UserDAO {

	User findByEmail(String email);
	
	List<User> GetAllUsers(); 
}
