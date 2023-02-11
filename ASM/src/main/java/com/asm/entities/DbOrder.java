package com.asm.entities;

import java.io.Serializable;
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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Orders")
public class DbOrder implements Serializable{
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
	@JoinColumn(name = "orderStatusId")
	DbOrderStatus ordersStatus;
	
	@OneToMany(mappedBy = "order")
	List<DbOrderDetail> orderDetails;
}
