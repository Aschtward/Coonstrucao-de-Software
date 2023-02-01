package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.ClienteModels;

@Repository
public interface ClientRepository extends JpaRepository<ClienteModels, Long>{
	
	Optional<ClienteModels> findByEmail(String email);

}
