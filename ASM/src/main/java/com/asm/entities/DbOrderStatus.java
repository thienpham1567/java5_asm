package com.asm.entities;

import java.io.Serializable;
import java.util.ArrayList;
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
	List<DbOrder> orders = new ArrayList<>();

	public int getOrdersStatusId() {
		return ordersStatusId;
	}

	public void setOrdersStatusId(int ordersStatusId) {
		this.ordersStatusId = ordersStatusId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<DbOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<DbOrder> orders) {
		this.orders = orders;
	}
}
