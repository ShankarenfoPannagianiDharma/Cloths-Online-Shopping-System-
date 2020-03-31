package group4.controller;

import java.util.ArrayList;
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

import group4.dao.UserDAO;
import group4.dao.OrdersDAO;
import group4.dao.ProductsDAO;
import group4.model.*;

@Controller
@RequestMapping("/product")
public class ProductController 
{

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
		
		//TODO: Display session cart
		//get cart from session
		List<OrderDetail> cartItems = user.getSelectedProducts();
		model.addAttribute("orderdetail",cartItems);
		//add cart information
		model.addAttribute("orderAmount", user.getOrderItemCounts());
		model.addAttribute("orderCost", user.getOrderTotalCosts());	

		//model.addAttribute("orderdetail", orderdetail);
		return "cart";
	}
	
	//add to cart button triggers this post mapping- contains Product object with only ID.
		@PostMapping("/addToCart")            //product from form is in this model
	public String selected(HttpSession session, @ModelAttribute("Product") Product product, Model model) {
		User user = (User) session.getAttribute("user");
		int productId = product.getProductID();

		//(PLACEHOLDER) get order of customer -> add item to cart
		//List<Order> orders = ordersDao.getCustomersOrders(user.getId());
		//ordersDao.addCartItem(orders.get(orders.size() - 1).getOrderID(), productId, user.getId());
		
		//get session attribute: cart
		List<OrderDetail> userCart = (List<OrderDetail>) user.getSelectedProducts();
		//if cart doesn't exist, add new list
		if(userCart == null)
		{userCart = new ArrayList<OrderDetail>();}
		//add the selected item to cart
		//catch: does item already exist in list?
		OrderDetail item = user.itemInCart(productId);
		if(item != null)
		{
			//if true, increment the order amount.
			item.incrementAmount();
		}
		else
		{
			OrderDetail orderDItem = new OrderDetail();
			orderDItem.setItemid(productId);
			orderDItem.setAmount(1);
			orderDItem.setPrice(productsDao.getProductCost(productId));
			userCart.add(orderDItem);
		}
		//save changes? is this line necessary?
		user.setSelectedProducts(userCart);

		//model.addAttribute("message", "Item added to cart.");
		return "redirect:list";
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
	//TODO: make obsolete. Should not be a thing
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
