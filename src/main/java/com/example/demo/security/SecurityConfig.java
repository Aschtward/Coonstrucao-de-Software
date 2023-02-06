package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		 http.authorizeHttpRequests()
		 .and()
		 .authorizeHttpRequests()
		 .requestMatchers("/css/**")
		 .permitAll()
		 .and()
		 .authorizeHttpRequests()
		 .requestMatchers("/img/**")
		 .permitAll()
		 .and()
		 .authorizeHttpRequests()
		 .requestMatchers("/createaccount")
		 .permitAll()
		 .and()
		 .authorizeHttpRequests()
		 .requestMatchers("/changePassword/**")
		 .permitAll()
		 .and()
		 .authorizeHttpRequests()
		 .requestMatchers("/forgotPassword")
		 .permitAll()
		 .and()
		 .authorizeHttpRequests()
		 .requestMatchers("/confirmAccount/**")
		 .permitAll()
         .anyRequest()
         .authenticated()
         .and()
         .formLogin()
         .loginPage("/loginPage")
         .usernameParameter("email")
         .permitAll()
         .and()
         .logout()
         .permitAll()
         .and()
         .csrf().disable();
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
