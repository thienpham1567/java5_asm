package com.asm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.asm.dao.ProductDAO;
import com.asm.entities.DbProduct;

@Controller
public class UserController {
	@Autowired 
	ProductDAO productDao;
	
	@GetMapping("/")
	public String index(final Model model) {
		return "/user/index";
	}
	
	@ModelAttribute("products")
	public List<DbProduct> getAllProducts(){
		return productDao.findAll();
	}
}
