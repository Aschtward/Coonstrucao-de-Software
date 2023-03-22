package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.dao.ProdutoCompradoDAO;
import com.example.demo.dto.AnuncioDTO;

@Controller
public class ProdutoController {
	
	@Autowired
	private ProdutoCompradoDAO produtoCompradoDao;
	
	@PostMapping("/fecharCompra")
	public RedirectView fecharCompra(@RequestBody List<AnuncioDTO> anunciosSelecionados, @RequestParam String id) {
		if(!anunciosSelecionados.isEmpty()) {
			produtoCompradoDao.fecharCompra(anunciosSelecionados,id);
		}
		return new RedirectView("/");
	}
	
	@GetMapping("/fecharCompra")
	public ModelAndView mostrarCarrinho() {
		ModelAndView fecharCompraView = new ModelAndView("fecharCompra");
		fecharCompraView.addObject("enderecos", produtoCompradoDao.buscarClienteAtual().getEndereco());
		return fecharCompraView;
	}
	
	

}
