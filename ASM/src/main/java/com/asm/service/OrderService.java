package com.asm.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asm.repository.OrderRepository;
import com.asm.entities.DbOrder;
import com.asm.interfaces.DatabaseService;


@Service
public class OrderService implements DatabaseService<DbOrder>{
	@Autowired 
	OrderRepository repo;

	@Override
	public java.util.List<DbOrder> getAll(boolean isSort) {
		return repo.findAll();
	}

	@Override
	public Optional<DbOrder> findById(int id) {
		Optional<DbOrder> dbUser = repo.findById(id);
		return dbUser;
	}


	@Override
	public DbOrder update(DbOrder model) {
		return repo.save(model);
	}

	@Override
	public void delete(int id) {
		repo.deleteById(id);
		
	}
}
