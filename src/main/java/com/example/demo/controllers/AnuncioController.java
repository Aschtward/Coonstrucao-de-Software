package com.example.demo.controllers;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.AnuncioDAO;

@Controller
public class AnuncioController {
	
	@Autowired
	AnuncioDAO anuncioDao;
	
	@GetMapping("/cadastrarAnuncio")
	public String novoAnuncio() {
		return "create_sale";
	}
	
	@PostMapping("/cadastrarAnuncio")
	public String cadastrarAnuncio(@RequestParam String preco, @RequestParam String nome, @RequestParam MultipartFile imagem) {
		anuncioDao.inserirAnuncio(nome, new BigDecimal(preco), imagem);
		return "create_sale";
	}
}
