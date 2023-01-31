package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.adapter.ClientAdapter;
import com.example.demo.models.Cliente;
import com.example.demo.repository.ClientRepository;

@Component
public class ClientDAO {
	
	@Autowired
	ClientRepository clientRepo;
	@Autowired
	ClientAdapter clientAdapter;
	
	public Cliente adicionarCliente( String name, String email, String password) {	
		BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
		Cliente cliente = clientAdapter.geraCliente(name, email, enconder.encode(password));
		clientRepo.save(cliente);
		return cliente;
	}

}
