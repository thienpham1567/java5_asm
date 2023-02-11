package com.asm.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "OrdersStatus")
public class DbOrderStatus implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int ordersStatusId;
	
	String description;
	
	@OneToMany(mappedBy = "ordersStatus")
	List<DbOrder> orders;
}
