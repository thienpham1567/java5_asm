package com.asm.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.asm.entities.DbProduct;
import com.asm.service.ProductService;

@Controller
public class ProductAdminController {
	@Autowired 
	ProductService productService;
	
	@ModelAttribute("products")
	public List<DbProduct> getAllProducts(){
		return productService.getAll(false);
	}
}
