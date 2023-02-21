package com.asm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asm.entities.DbOrderStatus;
import com.asm.interfaces.DatabaseService;
import com.asm.repository.OrderStatusRepository;

@Service
public class OrderStatusService implements DatabaseService<DbOrderStatus>{

	@Autowired
	private OrderStatusRepository repo;
	
	@Override
	public List<DbOrderStatus> getAll(boolean isSort) {
		List<DbOrderStatus> orderStatus = repo.findAll();
		return orderStatus;
	}

	@Override
	public Optional<DbOrderStatus> findById(int id) {
		Optional<DbOrderStatus> orderStatus = repo.findById(id);
		return orderStatus;
	}

	@Override
	public DbOrderStatus update(DbOrderStatus model) {
		
		return repo.save(model);
	}

	@Override
	public void delete(int id) {
		repo.deleteById(id);;
	}
}
