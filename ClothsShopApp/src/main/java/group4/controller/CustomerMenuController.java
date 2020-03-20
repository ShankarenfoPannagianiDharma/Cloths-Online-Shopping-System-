package group4.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import group4.dao.ProductsDAO;
import group4.model.Product;
import group4.model.User;

@Controller
@SessionAttributes("user")
public class CustomerMenuController 
{
	@Autowired
	ProductsDAO productsDAO;
	
	@GetMapping("/Menu")
	public String menu(HttpSession session, Model model) 
	{
		//if there is no user session
	    User user = (User) session.getAttribute("user");
	    if(user == null) 
	    { return "redirect:login"; }
	   
	    //get list of all products
	    List<Product> products = productsDAO.getAllProducts();
	    model.addAttribute("allProducts", products);
	    return "Menu";
	}
	
		
	//Control for productDetails: when an item is selected --> HAS GET PARAM PRODUCTID
	@GetMapping("/itemDetail")
	public String itemdetail(HttpSession session, @RequestParam("id") int id, Model model)
	{
		//if there is no user session
	    User user = (User) session.getAttribute("user");
	    if(user == null) 
	    { return "redirect:login"; }
	    
	    //get specific product
	    Product targetProduct = productsDAO.getProductFromId(id);
		model.addAttribute("targetProduct", targetProduct);
	    
	    return "ItemDetails";
	}
}
