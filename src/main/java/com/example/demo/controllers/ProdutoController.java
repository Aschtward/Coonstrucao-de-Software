package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.dao.ProdutoCompradoDAO;
import com.example.demo.dto.AnuncioDTO;

@Controller
public class ProdutoController {
	
	@Autowired
	private ProdutoCompradoDAO produtoCompradoDao;
	
	@PostMapping("/fecharCompra")
	public RedirectView fecharCompra(@RequestBody List<AnuncioDTO> anunciosSelecionados) {
		if(!anunciosSelecionados.isEmpty()) {
			produtoCompradoDao.fecharCompra(anunciosSelecionados);
		}
		return new RedirectView("/");
	}
	
	

}
