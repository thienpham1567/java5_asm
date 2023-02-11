package com.asm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.asm.entities.DbProduct;
import com.asm.models.Product;
import com.asm.repository.ProductRepository;
import com.asm.service.ProductService;

@Controller
public class UserController {
	@Autowired 
	ProductService productService;
	
	@GetMapping("/")
	public String index(final Model model) {
		return "/user/index";
	}
}
