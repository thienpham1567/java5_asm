package com.asm.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "OrderDetails")
public class DbOrderDetail implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int orderDetailId;
	
	Double detailPrice;
	int quantity;
	
	@ManyToOne
	@JoinColumn(name = "orderId")
	DbOrder order;
	
	@ManyToOne
	@JoinColumn(name = "productId")
	DbProduct product;
	
}
