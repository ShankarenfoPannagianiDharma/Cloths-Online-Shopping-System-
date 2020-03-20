package group4.controller;

import java.util.List;

/* Controller for logins.
 * Depending on database user admin int value, 
 * successful logins go to either "Menu.jsp" OR "AdminMenu.jsp"
 * 
 */

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import group4.dao.UserDAO;
import group4.model.LoginInfo;
import group4.model.User;

@Controller
@SessionAttributes("user")
public class LoginController {
	
	@Autowired
	UserDAO userDao;
	
	/**
	 * Create new signUpForm object for empty from
	 * 
	 * @return
	 */
	@ModelAttribute("LoginInfo")
	public LoginInfo loginForm() 
	{
	    return new LoginInfo();
	}

	/**
	 * Method to show the initial HTML form
	 * 
	 * @return
	 */
	@GetMapping("/login")
	public String login(HttpSession session, Model model) 
	{
		//if there is a user session already
	    User user = (User) session.getAttribute("user");
	    if(user != null) 
	    {
	    	if(user.isAdmin())
	    	{ return "AdminMenu"; }
	    	return "redirect:Menu";
	    }
	   
	    //get list of available users
	    List<User> users = userDao.GetAllUsers();
	    model.addAttribute("allUsers", users);
	    return "login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) 
	{
		//removes session stored
	    User user = (User) session.getAttribute("user");
	    if(user != null) {
	    	session.setAttribute("user", null);
	    	session.removeAttribute("user");
	    }
	    return "logout";
	}

	@PostMapping("/login") 
	public String login(@ModelAttribute("loginInfo") LoginInfo loginInfo, Model model)
	{
		User user = userDao.findByEmail(loginInfo.getEmail());
		model.addAttribute("message", "Login Fail");
		if(user != null && user.getPassword().equals(loginInfo.getPassword())) 
		{
			model.addAttribute("user", user);
			model.addAttribute("message", "Login Successful");
			if(user.isAdmin())
			{return "redirect:AdminMenu";}
			return "redirect:Menu";
		}
		return "login";
	}
	
}
