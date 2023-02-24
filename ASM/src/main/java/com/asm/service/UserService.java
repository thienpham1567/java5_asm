package com.asm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.Cookie;
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
import com.asm.webConfig.HashUtil;

@Service
public class UserService implements UserDetailsService, DatabaseService<DbUser> {

	@Autowired
	UserRepository repo;

	@Autowired
	CookieService cookieService;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		DbUser user = repo.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new CustomUserDetails(user);
	}

	public DbUser login(String email, String password) {
		DbUser user = this.repo.findByEmail(email);
		boolean isPasswordMatch = false;
		if(user != null) {
			isPasswordMatch = HashUtil.verify(password, user.getPassword());
			if(isPasswordMatch) {
				System.out.print(isPasswordMatch);
				System.out.print(user.getPassword());
				Cookie cookie = cookieService.add("userEmail", user.getEmail(), 1);
				System.out.print(cookie.getValue());
			}
		}
		return isPasswordMatch ? user : null;
	}

	@Override
	public List<DbUser> getAll(boolean isSort) {
		return this.repo.findAll();
	}

	@Override
	public Optional<DbUser> findById(int id) {
		return this.repo.findById(id);
	}
	
	public DbUser findByEmail(String email) {
		return this.repo.findByEmail(email);
	}

	@Override
	public DbUser update(DbUser model) {
		return this.repo.save(model);
	}

	@Override
	public void delete(int id) {
		this.repo.deleteById(id);
	}
}