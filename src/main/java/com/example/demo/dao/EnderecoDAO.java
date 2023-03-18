package com.example.demo.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.models.EnderecoModel;
import com.example.demo.repository.EnderecoRepository;

@Component
public class EnderecoDAO {
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	public EnderecoModel mudarEndereco(String bairro, String rua, String cidade, String numero,String nome) {
		EnderecoModel endereco = new EnderecoModel();
		endereco.setBairro(bairro);
		endereco.setCidade(cidade);
		endereco.setNumero(numero);
		endereco.setRua(rua);
		endereco.setNome(nome);
		enderecoRepository.save(endereco);
		return endereco;
	}
	
	public void alterarEndereco(String bairro, String rua, String cidade, String numero,String nome, String id) {
		Optional<EnderecoModel> endereco = enderecoRepository.findById(Long.parseLong(id));
		if(endereco.isPresent()) {
			endereco.get().setBairro(bairro);
			endereco.get().setCidade(cidade);
			endereco.get().setNumero(numero);
			endereco.get().setRua(rua);
			endereco.get().setNome(nome);
			enderecoRepository.save(endereco.get());
		}
	}
	
	public Optional<EnderecoModel> buscarEndereco(String id) {
		return enderecoRepository.findById(Long.parseLong(id));
	}
}
