package com.example.demo;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@TestConfiguration
public class SecurityContext {
	@Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoderTest() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return new ProviderManager(Collections.singletonList(authenticationProvider()));
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoderTest());
        return provider;
    }

    @Bean
    public UserDetailsManager userDetailsManager() throws Exception {
        return new InMemoryUserDetailsManager(Collections.singletonList(userDetails()));
    }

    @Bean
    public UserDetails userDetails() {
        return User.withUsername("user")
                .password(passwordEncoderTest().encode("password"))
                .roles("USER")
                .build();
    }
    @Bean
    public UserDetails getAuthentication(){
        return User.withUsername("user")
                .password(passwordEncoderTest().encode("password"))
                .roles("USER")
                .build();
    }
}
