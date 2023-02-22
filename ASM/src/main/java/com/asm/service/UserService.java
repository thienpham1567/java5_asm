package com.asm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.asm.entities.DbUser;
import com.asm.interfaces.DatabaseService;
import com.asm.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired 
	UserRepository repo;

	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		DbUser user = repo.findByEmail(username);		
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new CustomUserDetails(user);

	}
}