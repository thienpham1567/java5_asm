package com.asm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asm.entities.DbBrand;
import com.asm.entities.DbProduct;
import com.asm.interfaces.DatabaseService;
import com.asm.repository.BrandRepository;
import com.asm.repository.ProductRepository;

@Service
public class BrandService implements DatabaseService<DbBrand>{
	@Autowired
	private BrandRepository repo;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Override
	public List<DbBrand> getAll(boolean isSort) {
		return repo.findAll();
	}

	@Override
	public Optional<DbBrand> findById(int id) {
		return repo.findById(id);
	}

	@Override
	public DbBrand update(DbBrand model) {
		return repo.save(model);
	}

	@Override
	public void delete(int id) {
		boolean exists = productRepo.existsById(id);
		if(exists) {
			System.out.println("Cannot delete id " + id);
		}else {
			repo.deleteById(id);
		}
	}
	
	

	public List<DbBrand> findByName(String name){
		return repo.findByName(name);
	}

	public DbBrand getBrandById(int id){
		return repo.getById(id);
	}
}
