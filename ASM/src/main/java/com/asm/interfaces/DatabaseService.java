package com.asm.interfaces;

import java.util.List;
import java.util.Optional;

import com.asm.entities.DbUser;

public interface DatabaseService<T> {
	public List<T> getAll(boolean isSort);
	public Optional<T> findById(int id);
	public T update(T model);
	public void delete(int id);
}
