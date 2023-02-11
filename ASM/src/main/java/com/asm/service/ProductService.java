package com.asm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.asm.entities.DbProduct;
import com.asm.repository.ProductRepository;

public class ProductService {
	@Autowired
	private ProductRepository repo;
	
	public List<DbProduct> getAllProducts(){
		return repo.findAll();
	}
}
