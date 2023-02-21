package com.asm.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
	
	@GetMapping("/admin")
	public String getAdminPage(final Model model) {
		return "admin/index";
	}
	
	@GetMapping("/admin/brands")
	public String getAdminBrandsPage() {
		return "admin/brands";
	}
	
	@GetMapping("/admin/products")
	public String getAdminProductsPage() {
		return "admin/products";
	}
	
	@GetMapping("/admin/orders")
	public String getAdminOrdersPage() {
		return "admin/orders";
	}
}
