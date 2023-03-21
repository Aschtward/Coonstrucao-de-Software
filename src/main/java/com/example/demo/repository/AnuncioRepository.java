package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.AnuncioModel;

public interface AnuncioRepository extends JpaRepository<AnuncioModel, Long>{
	
	List<AnuncioModel> findByNameContaining(String name);

}
