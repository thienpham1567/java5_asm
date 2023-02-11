package com.asm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asm.entities.DbBrand;

public interface BrandRepository extends JpaRepository<DbBrand, Integer>{

}
