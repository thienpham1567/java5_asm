package com.asm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asm.entities.DbProduct;
import com.asm.interfaces.DatabaseService;
import com.asm.repository.ProductRepository;

@Service
public class ProductService implements DatabaseService<DbProduct>{
	@Autowired
	private ProductRepository repo;

	@Override
	public List<DbProduct> getAll(boolean isSort) {
		return repo.findAll();
	}

	@Override
	public Optional<DbProduct> findById(int id) {
		return repo.findById(id);
	}

	// update : update if found, otherwise insert
	@Override
	public DbProduct update(DbProduct product) {	
		return repo.save(product);
	}

	@Override
	public void delete(int id) {
		boolean exists = repo.existsById(id);
		if(exists) {
			repo.deleteById(id);
		}
	}
	
	public List<DbProduct> getAllProductByBrandName(String name){
		return repo.getAllProductByBrandName(name);
	}
}
