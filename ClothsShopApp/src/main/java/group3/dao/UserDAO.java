package group3.dao;

import java.util.List;

import group3.model.User;

 
public interface UserDAO {

	User findByEmail(String email);
	
	List<User> GetAllUsers(); 
}
