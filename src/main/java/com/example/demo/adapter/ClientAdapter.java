package com.example.demo.adapter;

import org.springframework.stereotype.Component;

import com.example.demo.models.Cliente;

@Component
public class ClientAdapter {
	
	public Cliente geraCliente(String name, String email, String password) {
		Cliente c = new Cliente();
		c.setEmail(email);
		c.setName(name);
		c.setPassword(password);
		return c;
	}

}
