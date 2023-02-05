package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.models.Cliente;
import com.example.demo.repository.ClientRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClienteDetailsServiceImpl implements UserDetailsService{

	@Autowired
	ClientRepository clienteRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Cliente cliente = clienteRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
		return new User(cliente.getEmail(), cliente.getPassword(),true,true,true, true, cliente.getAuthorities());
	}

}
