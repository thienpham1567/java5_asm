package com.asm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.asm.entities.DbOrderDetail;

public interface OrderDetailRepository extends JpaRepository<DbOrderDetail, Integer>{

}
