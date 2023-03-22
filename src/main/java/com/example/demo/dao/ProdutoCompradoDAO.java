package com.example.demo.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.AnuncioDTO;
import com.example.demo.models.AnuncioModel;
import com.example.demo.models.ClienteModels;
import com.example.demo.models.EnderecoModel;
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
	EnderecoDAO enderecoDao;
	@Autowired
	ClientRepository clienteRepository;
	@Autowired
	ProdutoRepository compraRepository;
	
	public void fecharCompra(List<AnuncioDTO> produtos,String id) {
		for(AnuncioDTO a : produtos) {
			ClienteModels comprador;
			Optional<ClienteModels> vendedor;
			Optional<AnuncioModel> anuncio;
			Optional<EnderecoModel> endereco = enderecoDao.buscarEndereco(id);
			anuncio = anuncioDao.buscarAnuncio(String.valueOf(a.getId()));
			if(anuncio.isPresent() && endereco.isPresent()) {
				vendedor = clienteRepository.findByAnuncio(anuncio.get());
				comprador = clienteDao.buscarSessaoCliente();
				ProdutoCompradoModel compra = new ProdutoCompradoModel();
				compra.setAnuncio(anuncio.get());
				compra.setQuantidade(a.getQuantidade());
				compra.setTotal(new BigDecimal(a.getPreco() * a.getQuantidade()));
				compra.setCliente(comprador);
				compra.setVendedor(vendedor.get());
				compra.setFoiAvaliado(false);
				compra.setEndereco(endereco.get());
				compraRepository.save(compra);
			}	
		}
	}
	public ClienteModels buscarClienteAtual() {
		return clienteDao.buscarSessaoCliente();
	}
	public List<ProdutoCompradoModel> buscarPorCliente(ClienteModels cliente){
		return compraRepository.findByCliente(cliente);
	}
	
	public List<ProdutoCompradoModel> buscarPorVendedor(ClienteModels vendedor){
		return compraRepository.findByVendedor(vendedor);
	}

	public void marcarAvaliada(String idCompra) {
		Optional<ProdutoCompradoModel> compra = compraRepository.findById(Long.parseLong(idCompra));
		if(compra.isPresent()) {
			compra.get().setFoiAvaliado(true);
			compraRepository.save(compra.get());
		}
		
	}

}
