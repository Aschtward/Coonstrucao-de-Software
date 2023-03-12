package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.ProdutoCompradoModel;

public interface ProdutoRepository extends JpaRepository<ProdutoCompradoModel, Long>{

}
