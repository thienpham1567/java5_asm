package com.asm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asm.entities.DbOrder;

public interface OrderRepository extends JpaRepository<DbOrder, Integer>{

}
