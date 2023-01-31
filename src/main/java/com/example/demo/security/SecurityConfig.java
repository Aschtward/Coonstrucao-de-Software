package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
		.httpBasic().and().authorizeHttpRequests()
		.requestMatchers("/css/**").permitAll().
		requestMatchers("/img/**").permitAll().
		requestMatchers(HttpMethod.GET, "/confirmAccount**").permitAll().
		requestMatchers(HttpMethod.GET, "/createaccount").permitAll().
		requestMatchers(HttpMethod.GET,"/login").permitAll().
		requestMatchers(HttpMethod.POST, "/createaccount").permitAll().anyRequest().authenticated()
		.and().formLogin().loginPage("/login").usernameParameter("email").and().csrf().disable();
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
