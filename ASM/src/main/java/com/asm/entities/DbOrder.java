package com.asm.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Orders")
public class DbOrder implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int orderId;

	String orderAddress;

	@Temporal(TemporalType.DATE)
	@Column(name = "Created")
	Date created = new Date();

	@Temporal(TemporalType.DATE)
	@Column(name = "Updated")
	Date updated = new Date();

	Double orderAmount;

	@ManyToOne
	@JoinColumn(name = "userId")
	DbUser user;

	@ManyToOne
	@JoinColumn(name = "ordersStatusId")
	DbOrderStatus ordersStatus;

	@OneToMany(mappedBy = "order")
	List<DbOrderDetail> orderDetails = new ArrayList<>();

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Double getOrderAmount() {
		double orderAmount = 0.0;
		for (DbOrderDetail item : orderDetails) {
			orderAmount += item.getDetailPrice();
		}
		return orderAmount;
	}

	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public DbUser getUser() {
		return user;
	}

	public void setUser(DbUser user) {
		this.user = user;
	}

	public DbOrderStatus getOrdersStatus() {
		return ordersStatus;
	}

	public void setOrdersStatus(DbOrderStatus ordersStatus) {
		this.ordersStatus = ordersStatus;
	}

	public List<DbOrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<DbOrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
}
