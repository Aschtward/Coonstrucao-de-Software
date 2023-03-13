package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.ClienteModels;
import com.example.demo.models.ProdutoCompradoModel;

public interface ProdutoRepository extends JpaRepository<ProdutoCompradoModel, Long>{
	
	List<ProdutoCompradoModel> findByCliente(ClienteModels cliente);
	List<ProdutoCompradoModel> findByVendedor(ClienteModels vendedor);
}
