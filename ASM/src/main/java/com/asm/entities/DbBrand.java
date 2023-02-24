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
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Brands")
public class DbBrand implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	int brandId;
	@NotEmpty
	String name;
	
	@OneToMany(mappedBy = "brand")
	List<DbProduct> products = new ArrayList<>();

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<DbProduct> getProducts() {
		return products;
	}

	public void setProducts(List<DbProduct> products) {
		this.products = products;
	}
}
