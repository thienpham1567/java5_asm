package com.asm.controller.admin;

import java.util.List;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import com.asm.entities.DbBrand;
import com.asm.repository.BrandRepository;
import com.asm.service.BrandService;
import com.asm.service.SessionService;

import antlr.StringUtils;



@Controller
public class BrandAdminController {
	@Autowired 
	BrandService brandService;
	
	@Autowired
	BrandRepository brandRepo;
	
	@ModelAttribute("brands")
	public List<DbBrand> getAllBrands(){
		return brandService.getAll(false);
	}
	
//	@GetMapping("/admin/brands")
//	public String getAdminBrandsPage(Model model) {
//		model.addAttribute("brands", getAllBrands());
//		model.addAttribute("brand", new DbBrand());
//		
//		return "admin/brands";
//	}
	

	
	@GetMapping("/admin/brands/edit/{id}")
	public String showEditForm(@PathVariable("id") int id, Model model) {
	    Optional<DbBrand> brand = brandService.findById(id);
	   
	   
	    
	    model.addAttribute("brand", brand);
	    return "admin/brands";
	}
	
	@PostMapping("/brands/save")
	public String save(@ModelAttribute("brand") DbBrand brand,BindingResult result, Model model) {
		
		if (brand.getName() == null || brand.getName().isEmpty()) {
	        result.addError(new FieldError("brand", "name", "Name is required"));
	    }
		// check for errors
	    if (result.hasErrors()) {
	        model.addAttribute("error", "There are errors in the form");
	        return "/admin/brands";
	    }
		//model.addAttribute("brand", new DbBrand());
		brandRepo.save(brand);
		
		return "redirect:/admin/brands";
		
	}
	
	@PostMapping("/brands/update/")
	public String updateBrand( @ModelAttribute("brand") DbBrand brand) {
	    brandService.update(brand);
	    return "redirect:/admin/brands";
	}

	
	@GetMapping("/admin/brands/delete/{id}")
	public String delete(@PathVariable("id") int id, Model model) {
	    DbBrand brand = brandService.findById(id).get();
	    model.addAttribute("brand", brand);
		brandRepo.delete(brand);
	   
		model.addAttribute("brands", getAllBrands());
	    
	    return "redirect:/admin/brands";
	}
	@Autowired
	SessionService session;
	
	@GetMapping("/admin/brands/search")
	public String search(Model model, 
			HttpServletRequest request
			) {
		String name = request.getParameter("name");
		
			List<DbBrand> brands = brandService.findByName(name);
			
			model.addAttribute("brands", brands);
		
		model.addAttribute("brand", new DbBrand());
		
	    return "/admin/brands";
	}
	
}
