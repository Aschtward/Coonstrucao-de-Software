package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.models.ClienteModels;
import com.example.demo.models.TokenModel;
import com.example.demo.repository.TokenRepository;

@Component
public class TokenDAO {

	@Autowired
	TokenRepository repository;
	
	public String gerarToken(ClienteModels cliente) {
		TokenModel token = new TokenModel(cliente);
		repository.save(token);
		return token.getRecoveryToken();
	}
	
	public TokenModel buscarToken(String token) {
		return repository.findByRecoveryToken(token);
	}
	
	public void removerToken(String token) {
		TokenModel tokenAux = repository.findByRecoveryToken(token);
		repository.delete(tokenAux);
	}
}
