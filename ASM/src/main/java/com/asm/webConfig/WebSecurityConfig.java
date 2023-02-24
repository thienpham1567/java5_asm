package com.asm.webConfig;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.asm.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserService userService;
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserService();
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
//		auth.authenticationProvider(authenticationProvider());
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.authenticationProvider(authenticationProvider());
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
		
//		auth.inMemoryAuthentication()
//		.withUser("user1").password(passwordEncoder().encode("123"))
//		.authorities("ROLE_USER");
//		==> cách này tạo 1 user có user1-123 lưu trong memory dùng để đăng nhập ko cần DB
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
//				.antMatchers("/").hasRole("USER")
				.antMatchers("*/admin/**").hasRole("ADMIN")
			.and().authorizeRequests().antMatchers("*/user/**").hasRole("USER")
//				.antMatchers("/","/register").permitAll()
				.anyRequest().permitAll()
			.and()
				.formLogin()
					.loginPage("/login")
//					
					.usernameParameter("email").passwordParameter("password")
					.loginProcessingUrl("/admin/index")
//					.defaultSuccessUrl("/");
			.and()
				.logout().logoutSuccessUrl("/").permitAll();
	}
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/webjars/**", "/css/**", "/img/**", "/js/**","/css_admin/**", "/img_admin/**", "/scc/**", "/vendor/**");
//	}
//	@Override
//	public void configure(WebSecurity web) throw Exception{
//		web.ignoring().antMatchers("/webjars/**", "/css/**", "/img/**", "/js/**","/css_admin/**", "/img_admin/**", "/scc/**", "/vendor/**").addResourceLocations(
//				"classpath:/META-INF/resources/webjars/", "classpath:/static/css/", "classpath:/static/img/",
//				"classpath:/static/js/", "classpath:/static/css_admin/", "classpath:/static/img_admin/", "classpath:/static/js/",
//				"classpath:/static/scss/", "classpath:/static/vendor/");
//	}
	
	
}
