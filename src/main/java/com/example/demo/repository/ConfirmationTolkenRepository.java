package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.ConfirmationTolkenModel;

public interface ConfirmationTolkenRepository extends JpaRepository<ConfirmationTolkenModel,String>{
	ConfirmationTolkenModel findByConfirmationToken(String confirmationToken);
}
