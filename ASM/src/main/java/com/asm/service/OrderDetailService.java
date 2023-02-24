package com.asm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asm.entities.DbOrderDetail;
import com.asm.interfaces.DatabaseService;
import com.asm.repository.OrderDetailRepository;

@Service
public class OrderDetailService implements DatabaseService<DbOrderDetail>{

	@Autowired
	private OrderDetailRepository repo;
	
	@Override
	public List<DbOrderDetail> getAll(boolean isSort) {
		List<DbOrderDetail> orderDetails = repo.findAll();
		return orderDetails;
	}

	@Override
	public Optional<DbOrderDetail> findById(int id) {
		Optional<DbOrderDetail> orderDetails = repo.findById(id);
		return orderDetails;
	}

	@Override
	public DbOrderDetail update(DbOrderDetail model) {
		return repo.save(model);
	}

	@Override
	public void delete(int id) {
		repo.deleteById(id);;
	}
}
