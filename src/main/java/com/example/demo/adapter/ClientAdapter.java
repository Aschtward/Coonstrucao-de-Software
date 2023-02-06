package com.example.demo.adapter;

import com.example.demo.models.ClienteModels;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClientAdapter {
	
	public void geraCliente(Optional<ClienteModels> optional, ClienteModels client) {
		if(optional.isPresent()) {
			client.setEmail(optional.get().getEmail());
			client.setId(optional.get().getId());
			client.setIsConfirmed(optional.get().getIsConfirmed());
			client.setName(optional.get().getName());
			client.setPassword(optional.get().getPassword());
			client.setRoles(optional.get().getRoles());
		}
	}

}
