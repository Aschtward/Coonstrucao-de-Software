package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.models.EnderecoModel;
import com.example.demo.repository.EnderecoRepository;

@Component
public class EnderecoDAO {
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	public void mudarEndereco(String bairro, String rua, String cidade, String numero) {
		EnderecoModel endereco = new EnderecoModel();
		endereco.setBairro(bairro);
		endereco.setCidade(cidade);
		endereco.setNumero(numero);
		endereco.setRua(rua);
		enderecoRepository.save(endereco);
	}
}
