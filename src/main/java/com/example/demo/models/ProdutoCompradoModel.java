package com.example.demo.models;

import java.math.BigDecimal;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class ProdutoCompradoModel {

	@Id
	@GeneratedValue(strategy  = GenerationType.AUTO)
	protected Long id;	
	@Column
	private int quantidade;
	@Column
	private BigDecimal total;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "anuncio")
	private AnuncioModel anuncio;
	@OneToOne
	@JoinColumn(name="comprador_id")
	private ClienteModels cliente;
	@OneToOne
	@JoinColumn(name="vendedor_id")
	private ClienteModels vendedor;
	@OneToOne
	@JoinColumn(name="endereco_id")
	private EnderecoModel endereco;
	@Column
	private boolean foiAvaliado;
	
	
	public ClienteModels getCliente() {
		return cliente;
	}
	public void setCliente(ClienteModels cliente) {
		this.cliente = cliente;
	}
	public ClienteModels getVendedor() {
		return vendedor;
	}
	public void setVendedor(ClienteModels vendedor) {
		this.vendedor = vendedor;
	}
	public boolean isFoiAvaliado() {
		return foiAvaliado;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setFoiAvaliado(boolean foiAvaliado) {
		this.foiAvaliado = foiAvaliado;
	}
	public AnuncioModel getAnuncio() {
		return anuncio;
	}
	public void setAnuncio(AnuncioModel anuncio) {
		this.anuncio = anuncio;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public EnderecoModel getEndereco() {
		return endereco;
	}
	public void setEndereco(EnderecoModel endereco) {
		this.endereco = endereco;
	}
	
	
}
