package com.asm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asm.entities.DbProduct;

@Repository
public interface ProductRepository extends JpaRepository<DbProduct, Integer>{

}
