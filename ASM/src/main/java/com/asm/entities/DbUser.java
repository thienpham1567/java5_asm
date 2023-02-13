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

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class DbUser implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int userId;
	
	String password;
	String firstname;
	String lastName;
	String email;
	String image;
	String userAddress;
	boolean isActive;
	boolean Admin;
	
	@OneToMany(mappedBy = "user")
	List<DbOrder> orders;
}
