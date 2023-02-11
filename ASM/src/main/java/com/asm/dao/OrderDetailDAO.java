package com.asm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.asm.entities.DbOrderDetail;

public interface OrderDetailDAO extends JpaRepository<DbOrderDetail, Integer>{

}
