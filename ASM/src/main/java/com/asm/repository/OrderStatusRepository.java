package com.asm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asm.entities.DbOrderStatus;

public interface OrderStatusRepository extends JpaRepository<DbOrderStatus, Integer>{

}
