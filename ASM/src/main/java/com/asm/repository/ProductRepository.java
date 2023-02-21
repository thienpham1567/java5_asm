package com.asm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.asm.entities.DbProduct;

public interface ProductRepository extends JpaRepository<DbProduct, Integer>{
	@Query(value = "select * from Brands b inner join Products p on b.BrandId = p.BrandId where b.Name = ?1", nativeQuery = true)
	List<DbProduct> getAllProductByBrandName(String name);
}
