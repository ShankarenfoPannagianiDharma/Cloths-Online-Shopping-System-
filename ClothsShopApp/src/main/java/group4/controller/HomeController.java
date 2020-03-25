package group4.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import group4.model.User;

@Controller
public class HomeController {
	@GetMapping("/")
	public String index(HttpSession session, Model model) {

		//if there is a user session already
	    User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);
		return "index";

	}

}
