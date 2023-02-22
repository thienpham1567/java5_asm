package com.asm.controller;

import java.util.Base64;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asm.service.CookieService;
import com.asm.service.CustomUserDetailsService;
import com.asm.service.UserService;

import com.asm.webConfig.*;



import com.asm.entities.*;
import com.asm.repository.UserRepository;



@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private HttpServletRequest request;
	
	@GetMapping("/login")
	public String viewHomePage() {
		return "security/login";
	}

	@PostMapping("/login")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password) {
		
//		HttpSession session= request.getSession();
		DbUser entity=this.userRepo.findByEmail(email);
		if (entity == null) {
//			System.out.println("1");
//			session.setAttribute("error", "Sai email hoặc password vui lòng nhập lại");
			return "redirect:/login";
		}
//		boolean checkpw=HashUtil.verify(password, entity.getPassword());
//		if (!checkpw) {
//			session.setAttribute("error", "Sai email hoặc password vui lòng nhập lại");
//			return "redirect:/login";
//		}
//		session.setAttribute("accounts", entity);
		return "redirect:/user/index";
	}

	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new DbUser());
		
		return "security/signup_form";
	}
	
	@PostMapping("/process_register")
	public String processRegister(DbUser user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		System.out.println(encodedPassword);
		userRepo.save(user);
		
		return "security/register_success";
	}
	
	@GetMapping("/users")
	public String listUsers(Model model) {
		List<DbUser> listUsers = userRepo.findAll();
		model.addAttribute("listUsers", listUsers);
		
		return "security/users";
	}
	
}