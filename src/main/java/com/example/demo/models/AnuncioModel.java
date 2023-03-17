package com.example.demo.models;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity(name = "tb_anuncio")
public class AnuncioModel {
	
	@Id
	@GeneratedValue(strategy  = GenerationType.AUTO)
	private Long id;
	@NotBlank
	@Column
	private String name;
	@Column
	@NotNull
	private BigDecimal preco;
	@Column
	private String image;
	@Column(columnDefinition="TEXT")
	private String descricao;
	@Column
	private int vendas;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "avaliacao_id")
	private List<AvaliacaoModel> avaliacao;
	@Column
	private int notaTotal;
	
	
	public int getNotaTotal() {
		return notaTotal;
	}

	public void setNotaTotal(int notaTotal) {
		this.notaTotal = notaTotal;
	}

	public List<AvaliacaoModel> getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(List<AvaliacaoModel> avaliacao) {
		this.avaliacao = avaliacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getId() {
		return id;
	}

	public int getVendas() {
		return vendas;
	}

	public void setVendas(int vendas) {
		this.vendas = vendas;
	}
	
}
