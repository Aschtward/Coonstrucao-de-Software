package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class AvaliacaoModel {
	
	@Id
	@GeneratedValue(strategy  = GenerationType.AUTO)
	private Long id;
	@OneToOne
	@JoinColumn(name = "cliente_id")
	private ClienteModels cliente;
	@Column
	private String avaliacao;
	@Column
	private int nota;
	
	public ClienteModels getCliente() {
		return cliente;
	}
	public void setCliente(ClienteModels cliente) {
		this.cliente = cliente;
	}
	public String getAvaliacao() {
		return avaliacao;
	}
	public void setAvaliacao(String avaliacao) {
		this.avaliacao = avaliacao;
	}
	public int getNota() {
		return nota;
	}
	public void setNota(int nota) {
		this.nota = nota;
	}
	
}
