package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.EnderecoModel;

public interface EnderecoRepository extends JpaRepository<EnderecoModel, Long>{

}
