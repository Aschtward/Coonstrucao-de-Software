package com.example.demo.models;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	
	
	
}
