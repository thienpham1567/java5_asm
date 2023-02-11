package com.asm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asm.entities.DbUser;
import com.asm.interfaces.DatabaseService;
import com.asm.repository.UserRepository;

@Service
public class UserService implements DatabaseService<DbUser> {
	
	@Autowired 
	UserRepository repo;

	@Override
	public List<DbUser> getAll(boolean isSort) {
		List<DbUser> dbUser = repo.findAll();
		return dbUser;
	}

	@Override
	public Optional<DbUser> findById(int id) {
		Optional<DbUser> dbUser = repo.findById(id);
		return dbUser;
	}

	@Override
	public DbUser update(DbUser model) {	
		return repo.save(model);
	}

	@Override
	public void delete(int id) {
		repo.deleteById(id);	
	}
	
}