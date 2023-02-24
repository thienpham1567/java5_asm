package com.asm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

import com.asm.service.CartService;
import com.asm.service.CookieService;
import com.asm.service.OrderDetailService;
import com.asm.service.OrderService;
import com.asm.service.OrderStatusService;
import com.asm.service.ProductService;
import com.asm.service.UserService;

import com.asm.entities.*;

import java.util.*;

@Controller
public class CartController {
	@Autowired 
	OrderService orderService;
	
	@Autowired 
	OrderStatusService orderStatusService;
	
	@Autowired 
	OrderDetailService orderDetailService;
	
	@Autowired
	CartService cartService;
	
	@Autowired 
	ProductService productService;
	
	@Autowired 
	UserService userService;
	
	@Autowired
	CookieService cookieService;
	
	@GetMapping("/cart")
	public String getCartPage(final Model model) {
		Collection<DbOrderDetail> items = cartService.getOrder();
		model.addAttribute("cart", items);
		model.addAttribute("total", cartService.getAmount());
		return "/cart/index";
	}
	
	@GetMapping("/add-product/{productId}")
	public RedirectView addProductToCart(@PathVariable("productId") int id,final Model model) {
		DbProduct p = productService.findById(id).get();
		cartService.add(1, p);
		return new RedirectView("/cart");
	}
	
	@GetMapping("/reduce-product/{productId}")
	public RedirectView reduceProductToCart(@PathVariable("productId") int id,final Model model) {
		DbProduct p = productService.findById(id).get();
		cartService.add(-1, p);
		return new RedirectView("/cart");
	}
	
	@GetMapping("/checkout")
	public RedirectView checkoutCart(final Model model) {
		String currentEmailUser = cookieService.getValue("userEmail");
		if(currentEmailUser.isEmpty()) {
			return new RedirectView("/login");
		}
		DbOrderStatus orderStatus = orderStatusService.findById(2).get();
		DbUser currentUser = userService.findByEmail(currentEmailUser);
		DbOrder order = new DbOrder();
		order.setOrderAddress("");
		order.setUser(currentUser);
		order.setOrdersStatus(orderStatus);
		Collection<DbOrderDetail> items = cartService.getOrder();
		orderService.update(order);
		double total = 0.0;
		for(DbOrderDetail item : items) {
			total += item.getDetailPrice();
			item.setDetailPrice(item.getDetailPrice());
			item.setOrder(order);
			orderDetailService.update(item);
		}
		order.setOrderAmount(total);
		orderService.update(order);
		return new RedirectView("/");
	}
	
	@ModelAttribute("productsInCart")
	public Collection<DbOrderDetail> getCart(){
		return cartService.getOrder();
	}
	
	@ModelAttribute("totalPrice")
	public double getTotalPrice() {
		return cartService.getAmount();
	}
}
