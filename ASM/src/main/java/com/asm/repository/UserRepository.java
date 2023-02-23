package com.asm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.asm.entities.DbUser;



@Repository
public interface UserRepository extends JpaRepository<DbUser, Integer>{
	public DbUser findByEmail(String email);
//	DbUser findByEmailAndPassword(String email, String password);
	
//	@Query("SELECT u FROM Users u WHERE u.email like ?1")
//	List<DbUser> findAllByEmail(String email);
//	public DbUser findByEmail(@Param("email") String email);
////	
//	@Query("SELECT p FROM Users p WHERE p.password like ?1")
//	public DbUser findByPw(@Param("password") String pw);
//	DbUser findByEmail(String email);
//	DbUser findById(String id);
//	
////	@Query(value="select * from user u where u.id = ?1 and u.role = ?2",nativeQuery = true)
//	DbUser findByIdAndRole(String id, String role);
//	
//	void deleteById(String id);
}
