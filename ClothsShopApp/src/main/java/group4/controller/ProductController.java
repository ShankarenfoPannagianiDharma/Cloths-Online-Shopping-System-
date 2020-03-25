package group4.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import group4.dao.UserDAO;
import group4.dao.OrdersDAO;
import group4.dao.ProductsDAO;
import group4.model.*;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	UserDAO userDao;

	@Autowired
	ProductsDAO productsDao;

	@Autowired
	OrdersDAO ordersDao;

	@ModelAttribute("Product")
	public Product selectedProduct() {
		return new Product();
	}

	/**
	 * Method to show the initial HTML form
	 * 
	 * @return
	 */
	@GetMapping("/list")
	public String showProducts(HttpSession session, Model model) {
		List<Product> products = productsDao.getAllProducts();
		model.addAttribute("products", products);
		return "show-products";
	}

	@GetMapping("/cart")
	public String showCart(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:login";
		}
		List<Order> orders = ordersDao.getCustomersOrders(user.getId());
		List<OrderDetail> orderdetail = ordersDao.getOrderDetail(orders.get(orders.size() - 1).getOrderID(),
				user.getId());

		model.addAttribute("orderdetail", orderdetail);
		return "cart";
	}
	
	@PostMapping("/addToCart")
	public String selected(HttpSession session, @ModelAttribute("Product") Product product, Model model) {
		User user = (User) session.getAttribute("user");
		int productId = product.getProductID();

		List<Order> orders = ordersDao.getCustomersOrders(user.getId());

		ordersDao.addCartItem(orders.get(orders.size() - 1).getOrderID(), productId, user.getId());

		return "redirect:cart";
	}

	// Control for productDetails: when an item is selected --> HAS GET PARAM
	// PRODUCTID
	@GetMapping("/itemDetail")
	public String itemdetail(HttpSession session, @RequestParam("id") int id, Model model) {
		// if there is no user session
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:login";
		}

		// get specific product
		Product targetProduct = productsDao.getProductFromId(id);
		model.addAttribute("targetProduct", targetProduct);

		return "ItemDetails";
	}
	
	//#Bao
	@GetMapping("/newCart")
	public String newCart(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:login";
		}
		ordersDao.createNewOrder(user.getId());
		return "newCart";	
	}
	
}
