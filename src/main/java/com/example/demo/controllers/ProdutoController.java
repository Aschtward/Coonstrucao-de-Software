package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.ProdutoDAO;

@Controller
public class ProdutoController {
	
	@Autowired
	private ProdutoDAO dao;
	
	@PostMapping("/adicionarProduto")
	public void adicionarProduto(@RequestParam String idProduto, @RequestParam int quantidade) {
		dao.adicionarProdutoCarrinho(idProduto, quantidade);
	}
	

}
