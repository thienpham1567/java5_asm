package com.asm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
	@GetMapping("/admin")
	public String getAdminPage(final Model model) {
		return "/admin/index";
	}
}
