package com.asm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.asm.service.CookieService;
import com.asm.service.UserService;

import com.asm.webConfig.*;

import com.asm.entities.*;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private CookieService cookieService;
	
	@GetMapping("/login")
	public String viewHomePage() {
		return "security/login";
	}

	@PostMapping("/login")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password) {
		DbUser currentUser = this.userService.login(email, password);
		if(currentUser.isAdmin() == true) {
			return currentUser != null ? "redirect:/admin" : "redirect:/security/login";
		}
		return currentUser != null ? "redirect:/" : "redirect:/security/login";
		
	}
	
	@GetMapping("/logout")
	public String logout(Model model) {
		cookieService.remove("cookie");
		return "/";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new DbUser());

		return "security/signup_form";
	}

	@PostMapping("/process_register")
	public String processRegister(DbUser user) {
		user.setPassword(HashUtil.hash(user.getPassword()));

		return this.userService.update(user) != null ? "security/register_success" : "";
	}

	@GetMapping("/users")
	public String listUsers(Model model) {
		List<DbUser> listUsers = this.userService.getAll(false);
		model.addAttribute("listUsers", listUsers);

		return "security/users";
	}

}