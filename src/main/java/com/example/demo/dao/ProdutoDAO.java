package com.example.demo.dao;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.models.AnuncioModel;
import com.example.demo.models.ProdutoCompradoModel;

@Component
public class ProdutoDAO {

	@Autowired
	private AnuncioDAO anuncioDao;
	@Autowired
	private ClientDAO clienteDao;
	
	public void adicionarProdutoCarrinho(String idProduto, int quantidade) {
		Optional<AnuncioModel> anuncioReferente = anuncioDao.buscarAnuncio(idProduto);
		if(anuncioReferente.isPresent()) {
			AnuncioModel produtoAnuncio = anuncioReferente.get();
			ProdutoCompradoModel produto  = new ProdutoCompradoModel();
			produto.setAnuncio(produtoAnuncio);
			produto.setQuantidade(quantidade);
			produto.setTotal(produtoAnuncio.getPreco().multiply(new BigDecimal(quantidade)));
			clienteDao.adicionarProdutoCarrinho(produto);
		}
	}
}
