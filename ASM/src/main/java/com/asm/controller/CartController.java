package com.asm.controller;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.asm.service.CartService;
import com.asm.service.OrderDetailService;
import com.asm.service.OrderService;
import com.asm.service.ProductService;

import com.asm.entities.*;

import java.util.*;

@Controller
public class CartController {
	@Autowired 
	OrderService orderService;
	
	@Autowired 
	OrderDetailService orderDetailService;
	
	@Autowired
	CartService cartService;
	
	@Autowired 
	ProductService productService;
	
	@GetMapping("/cart")
	public String getCartPage(final Model model) {
		Collection<DbOrderDetail> list = cartService.getOrder();
		model.addAttribute("cart", list);
		model.addAttribute("total", cartService.getAmount());
		model.addAttribute("values", 10);
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
	
	@ModelAttribute("productsInCart")
	public Collection<DbOrderDetail> getCart(){
		return cartService.getOrder();
	}
	
	@ModelAttribute("totalPrice")
	public double getTotalPrice() {
		return cartService.getAmount();
	}
}
