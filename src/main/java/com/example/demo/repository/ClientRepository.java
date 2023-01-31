package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Cliente;

@Repository
public interface ClientRepository extends JpaRepository<Cliente, Long>{
	
	Optional<Cliente> findByEmail(String email);

}
