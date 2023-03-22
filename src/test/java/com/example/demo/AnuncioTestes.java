package com.example.demo;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.models.AnuncioModel;
import com.example.demo.models.AvaliacaoModel;
import com.example.demo.repository.AnuncioRepository;

@DataJpaTest
public class AnuncioTestes {

	@Autowired
	private AnuncioRepository anuncioTeste;
	
	 
	@Test
	public void deveRetornarAnunciosParaPesquisa() throws Exception {
		//dado
		AnuncioModel anuncio = new AnuncioModel();
		anuncio.setName("Nome de teste");
		anuncio.setAvaliacao(new ArrayList<AvaliacaoModel>());
		anuncio.setDescricao("descricao");
		anuncio.setImage("imagem");
		anuncio.setNotaTotal(0);
		anuncio.setPreco(new BigDecimal(0));
		anuncio.setVendas(0);
		AnuncioModel anuncio2 = new AnuncioModel();
		anuncio2.setName("Nome que nao deve aparecer");
		anuncio2.setAvaliacao(new ArrayList<AvaliacaoModel>());
		anuncio2.setDescricao("descricao");
		anuncio2.setImage("imagem");
		anuncio2.setNotaTotal(0);
		anuncio2.setPreco(new BigDecimal(0));
		anuncio2.setVendas(0);
		//quando
		anuncioTeste.save(anuncio);
		anuncioTeste.save(anuncio2);
		//entao
		assert(anuncioTeste.findByNameContaining("teste").contains(anuncio));
		assert(!anuncioTeste.findByNameContaining("teste").contains(anuncio2));
	}
}
