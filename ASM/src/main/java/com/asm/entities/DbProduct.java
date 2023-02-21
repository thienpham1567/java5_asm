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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Products")
public class DbProduct implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int productId;

	String name;

	String image;

	Double price;

	int inStock;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "Created")
	Date created = new Date();
	
	boolean isAvailable;
	
	@ManyToOne
	@JoinColumn(name = "BrandId")
	DbBrand brand;
	
	@OneToMany(mappedBy = "product")
	List<DbOrderDetail> orderDetails;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getInStock() {
		return inStock;
	}

	public void setInStock(int inStock) {
		this.inStock = inStock;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public DbBrand getBrand() {
		return brand;
	}

	public void setBrand(DbBrand brand) {
		this.brand = brand;
	}

	public List<DbOrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<DbOrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
}
