package group4.controller;

import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import group4.dao.OrdersDAO;
import group4.dao.ProductsDAO;
import group4.dao.UserDAO;
import group4.model.Order;
import group4.model.User;

@Controller
@RequestMapping("/account")
public class AccountController 
{
	@Autowired
	UserDAO userDao;

	@Autowired
	ProductsDAO productsDao;

	@Autowired
	OrdersDAO ordersDao;
	
	//form to get orderDetails
	@ModelAttribute("orderModel")
	public Order selectedOrder() 
	{ return new Order(); }
	
	//base account page- list order purchases by group
	@GetMapping("/purchases")
	public String purchases(HttpSession session, Model model) 
	{
		User user = (User) session.getAttribute("user");
		if (user == null) 
		{ return "redirect:login"; }
		
		//get all orders by user
		List<Order> orders= ordersDao.getCustomersOrders(user.getId());
		
		model.addAttribute("orders",orders);
		return "customerorders";
	}
	
	//postmap from order listing- the contents of an order
	@PostMapping("orderInfo")
	public String orderInfo(HttpSession session,@ModelAttribute("orderModel") Order orderForm, Model model)
	{
		User user = (User) session.getAttribute("user");
		if (user == null) 
		{ return "redirect:login"; }
		
		//get the orderDetails of specific order
		Order datas = ordersDao.getSpecificOrder(orderForm.getOrderID());
	
		if(datas == null || datas.getPurchasedItems() == null)
		{
			model.addAttribute("orderd", null);
			//add cart information
			model.addAttribute("orderAmount", 0);
			model.addAttribute("orderCost", 0);
		}
		else
		{
			model.addAttribute("orderd", datas.getPurchasedItems());
			//add cart information
			DecimalFormat df2 = new DecimalFormat("#.##");
			model.addAttribute("orderAmount", datas.orderTotalNum());
			model.addAttribute("orderCost", df2.format(datas.orderTotalCosts()));
		}
		return "orderinfo";
	}
	

}
