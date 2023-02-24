package com.asm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.asm.entities.DbOrder;
@Repository
public interface OrderRepository extends JpaRepository<DbOrder, Integer>{
	@Query("SELECT o FROM DbOrder o WHERE DATEPART(month, o.created) = :month AND DATEPART(year, o.created) = :year")
	List<DbOrder> findByMonth(@Param("month") int month, @Param("year") int year);

}
