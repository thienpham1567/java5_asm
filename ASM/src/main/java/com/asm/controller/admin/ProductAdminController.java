package com.asm.controller.admin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asm.entities.DbProduct;
import com.asm.service.ProductService;

@Controller
public class ProductAdminController {
	@Autowired 
	ProductService productService;
	
	public static String imagesPath = "D://Java5//workspace_8//java5_asm//ASM//src//main//resources//static//img//";
	
	@ModelAttribute("products")
	public List<DbProduct> getAllProducts(){
		return productService.getAll(false);
	}
	
	@GetMapping("/admin/products")
	public String getAdminProductsPage(Model model) {
		DbProduct pro = new DbProduct();
		model.addAttribute("products", getAllProducts());
		model.addAttribute("product", pro);
		return "admin/products";
	}
	
	@GetMapping("/admin/products/edit/{productId}")
	public String edit(@PathVariable("productId") int productId, Model model) {
		Optional<DbProduct> product = productService.findById(productId);
		model.addAttribute("img", "/img/" + product.get().getImage());
		model.addAttribute("product", product);
		return "admin/products";
	}
	
	// upsert : update if found, otherwise insert
	@PostMapping("/products/save")
	public String updateProduct(@RequestParam("file") MultipartFile file, RedirectAttributes attributes, @ModelAttribute("product") DbProduct product, Model model) {	
		Optional<DbProduct> product1 = productService.findById(product.getProductId());
		if(product1.isPresent()) {
			DbProduct product2 = new DbProduct();
			product2.setImage(product1.get().getImage());
		    return "redirect:/admin/products";
		}else {
			if (file.isEmpty()) {
	            return "redirect:/admin/products";
	        }
			try {
				byte[] bytes = file.getBytes();
				Path path = Paths.get(imagesPath + file.getOriginalFilename());
				System.out.println(path);
				Files.write(path, bytes);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			product.setImage(file.getOriginalFilename());
		    productService.update(product);
			return "redirect:/admin/products";
		}
	}
	
	@GetMapping("/admin/products/delete/{id}")
	public String delete(@PathVariable("id") int id, Model model) {
	    DbProduct product = productService.findById(id).get();
	    model.addAttribute("product", product);
		productService.delete(product.getProductId());
		model.addAttribute("products", getAllProducts());
	    return "redirect:/admin/products";
	}
}
