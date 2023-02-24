package com.asm.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.asm.entities.DbBrand;


public interface BrandRepository extends JpaRepository<DbBrand, Integer>{
	@Query("SELECT o FROM DbBrand o WHERE o.name LIKE %?1%")
	List<DbBrand> findByName(String name);
	
	
}
