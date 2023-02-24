package com.asm.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.asm.service.ProductService;

@Controller
public class AdminController {
	@Autowired 
	ProductService productService;
	
	@GetMapping("/admin")
	public String getAdminPage(final Model model) {
		return "admin/index";
	}
	
//	@GetMapping("/admin/brands")
//	public String getAdminBrandsPage() {
//		return "admin/brands";
//	}
	
	@GetMapping("/admin/orders")
	public String getAdminOrdersPage() {
		return "admin/orders";
	}
	
	
}
