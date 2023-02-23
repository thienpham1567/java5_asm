package com.asm.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.asm.entities.DbBrand;
import com.asm.entities.DbProduct;

import com.asm.service.BrandService;
import com.asm.service.CartService;
import com.asm.service.ProductService;

@Controller
public class ProductController {
	@Autowired 
	ProductService productService;
	
	@Autowired 
	BrandService brandService;
	
	@Autowired
	CartService cartService;
	
	@GetMapping("/")
	public String getUserPage(final Model model) {
		return "/user/index";
	}
	
	// load product by brand name
	@GetMapping("/brand/{name}")
	public String getProductByBrandName(@PathVariable("name") String name, Model model) {
		List<DbProduct> listProduct = productService.getAllProductByBrandName(name);
		model.addAttribute("products", listProduct);
		return "/user/index";
	}
	
	@GetMapping("/products/{id}")
	public String getProduct(@PathVariable("id") int id, Model model) {
		Optional<DbProduct> product = productService.findById(id);
		model.addAttribute("product", product.get());
		return "/product/index";
	}
	
	@ModelAttribute("products")
	public List<DbProduct> getAllProducts(){
		return productService.getAll(false);
	}
	
	@ModelAttribute("brands")
	public List<DbBrand> getAllBrands(){ 
		return brandService.getAll(false);
	}
}
