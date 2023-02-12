package com.asm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.asm.service.OrderDetailService;
import com.asm.service.OrderService;

@Controller
public class CartController {
	@Autowired 
	OrderService orderService;
	
	@Autowired 
	OrderDetailService orderDetailService;
	
	@GetMapping("/cart")
	public String getCartPage(final Model model) {
		return "/cart/index";
	}
}
