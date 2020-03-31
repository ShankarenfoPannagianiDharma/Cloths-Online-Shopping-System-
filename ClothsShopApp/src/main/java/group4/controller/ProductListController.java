package group4.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import group4.dao.UserDAO;

import group4.dao.ProductsDAO;
import group4.model.*;

@Controller
@SessionAttributes("User")
public class ProductListController {
	
	@Autowired
	UserDAO userDao;
	
	@Autowired 
	ProductsDAO productsDao;
	
	@ModelAttribute("Product")
	public Product selectedProduct() {
		return new Product();
	}

	/**
	 * Method to show the initial HTML form
	 * 
	 * @return
	 */
	@GetMapping("/showProducts")
	public String showProducts(HttpSession session, Model model) {
	    Product product = (Product) session.getAttribute("product");
	    if(product != null) {
	    	List<Product> products = productsDao.getAllProducts();
	    	model.addAttribute("products", products);
	    	return "show-products";
	    }
	    return "login";
	}
	
	@PostMapping("/selectedProduct")
	public String selected(HttpSession session, @ModelAttribute("product") Product product, Model model) {
		User user = (User) session.getAttribute("user");
		int productId = product.getProductID();
		
		/*for(OrderDetail p : user.getSelectedProducts()) {
			if(p.getItemid()() == productId) {
				model.addAttribute("message", "Product already selected.");
				return "redirect:showProducts";
			}
		}*/
		
		/*if(userDao.selectedProductByProductID(user.getEmail(),productId) > 0) {
			user.setSelectedProducts(userDao.findSelectedProducts(user.getEmail()));
		}*/
		
		return "redirect:login";
	}

}
