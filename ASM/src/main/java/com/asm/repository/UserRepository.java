package com.asm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asm.entities.DbUser;

public interface UserRepository extends JpaRepository<DbUser, Integer>{

}
