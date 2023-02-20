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
	
	double detailPrice;
	int quantity;
	
	@ManyToOne
	@JoinColumn(name = "orderId")
	DbOrder order;
	
	@ManyToOne
	@JoinColumn(name = "productId")
	DbProduct product;

	public int getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(int orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public Double getDetailPrice() {
		return product.getPrice() * this.quantity;
	}

	public void setDetailPrice(Double detailPrice) {
		this.detailPrice = detailPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public DbOrder getOrder() {
		return order;
	}

	public void setOrder(DbOrder order) {
		this.order = order;
	}

	public DbProduct getProduct() {
		return product;
	}

	public void setProduct(DbProduct product) {
		this.product = product;
	}
}
