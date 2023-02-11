package com.asm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asm.entities.DbProduct;

@Repository
public interface ProductDAO extends JpaRepository<DbProduct, Integer>{

}
