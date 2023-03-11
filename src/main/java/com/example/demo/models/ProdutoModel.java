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

@Entity
public class ProdutoModel {

	@Id
	@GeneratedValue(strategy  = GenerationType.AUTO)
	protected Long id;	
	@Column
	private int quantidade;
	@Column
	private BigDecimal total;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "anuncio_id")
	private AnuncioModel anuncio;
	
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
}
