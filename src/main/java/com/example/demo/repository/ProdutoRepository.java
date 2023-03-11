package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.ProdutoModel;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long>{

}
