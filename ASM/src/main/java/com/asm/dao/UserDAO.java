package com.asm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asm.entities.DbUser;

public interface UserDAO extends JpaRepository<DbUser, Integer>{

}
