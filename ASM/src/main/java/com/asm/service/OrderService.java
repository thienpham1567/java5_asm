package com.asm.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asm.repository.OrderRepository;

import antlr.collections.List;
import com.asm.entities.DbOrder;
import com.asm.interfaces.DatabaseService;


@Service
public class OrderService implements DatabaseService<DbOrder>{
	@Autowired 
	OrderRepository repo;

	@Override
	public java.util.List<DbOrder> getAll(boolean isSort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<DbOrder> findById(int id) {
		Optional<DbOrder> dbUser = repo.findById(id);
		return dbUser;
	}

	@Override
	public DbOrder create(DbOrder model) {
		repo.save(model);
		return model;
	}

	@Override
	public DbOrder update(DbOrder model) {
		repo.save(model);
		return null;
	}

	@Override
	public void delete(int id) {
		repo.deleteById(id);
		
	}

	
}
