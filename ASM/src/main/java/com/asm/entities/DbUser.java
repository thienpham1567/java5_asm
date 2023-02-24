package com.asm.entities;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
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
@Table(name = "Users")
public class DbUser implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int userId;
	
	private String password;
	@Column(name = "firstname")
	private String firstname;
	@Column(name = "lastname")
	private String lastName;
	
	@Column(unique = true ,nullable = false)
	private String email;
	
	private String image;
	private String userAddress;
	boolean isActive;
	@Column(name = "admin")
	boolean Admin;
	
	
	@OneToMany(mappedBy = "user")
	List<DbOrder> orders = new ArrayList<>();
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isAdmin() {
		return Admin;
	}

	public void setAdmin(boolean admin) {
		Admin = admin;
	}

	public List<DbOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<DbOrder> orders) {
		this.orders = orders;
	}
}
