package com.example.demo.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.AnuncioDTO;
import com.example.demo.models.AnuncioModel;
import com.example.demo.models.ClienteModels;
import com.example.demo.models.ProdutoCompradoModel;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.ProdutoRepository;


@Component
public class ProdutoCompradoDAO {
	
	@Autowired
	ClientDAO clienteDao;
	@Autowired
	AnuncioDAO anuncioDao;
	@Autowired
	ClientRepository clienteRepository;
	@Autowired
	ProdutoRepository compraRepository;
	
	public void fecharCompra(List<AnuncioDTO> produtos) {
		for(AnuncioDTO a : produtos) {
			ClienteModels comprador;
			Optional<ClienteModels> vendedor;
			Optional<AnuncioModel> anuncio;
			anuncio = anuncioDao.buscarAnuncio(String.valueOf(a.getId()));
			if(anuncio.isPresent()) {
				vendedor = clienteRepository.findByAnuncio(anuncio.get());
				comprador = clienteDao.buscarSessaoCliente();
				ProdutoCompradoModel compra = new ProdutoCompradoModel();
				compra.setAnuncio(anuncio.get());
				compra.setQuantidade(a.getQuantidade());
				compra.setTotal(new BigDecimal(a.getPreco()));
				compra.setCliente(comprador);
				compra.setVendedor(vendedor.get());
				compraRepository.save(compra);
			}	
		}
	}
	
	public List<ProdutoCompradoModel> buscarPorCliente(ClienteModels cliente){
		return compraRepository.findByCliente(cliente);
	}
	
	public List<ProdutoCompradoModel> buscarPorVendedor(ClienteModels vendedor){
		return compraRepository.findByVendedor(vendedor);
	}

}
