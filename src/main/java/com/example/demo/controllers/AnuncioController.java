package com.example.demo.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.dao.AnuncioDAO;
import com.example.demo.dao.ClientDAO;
import com.example.demo.models.AnuncioModel;

@Controller
public class AnuncioController {
	
	@Autowired
	AnuncioDAO anuncioDao;
	@Autowired
	ClientDAO clienteDao;
	
	@PostMapping("/cadastrarAnuncio")
	public RedirectView cadastrarAnuncio(@RequestParam String preco, @RequestParam String nome,@RequestParam String descricao, @RequestParam MultipartFile imagem) {
		anuncioDao.inserirAnuncio(nome, new BigDecimal(preco), imagem,descricao);
		return new RedirectView("/anunciante");
	}
	
	@PostMapping("/avaliarProduto")
	public RedirectView avaliarProduto(@RequestParam String id, @RequestParam String nota, @RequestParam String avaliacao,@RequestParam String idCompra) {
		anuncioDao.avaliarProduto(id,nota,avaliacao, idCompra);
		return new RedirectView("/perfil");
	}
	
	@GetMapping("/")
	public ModelAndView exibirTodosAnuncios() {
		List<AnuncioModel> anuncios = anuncioDao.exibirTodos();
		ModelAndView anuncioView = new ModelAndView("index");
		List<AnuncioModel> anuncioMaisVendido = anuncioDao.maisvendido();
		anuncioView.addObject("anuncios",anuncios);
		anuncioView.addObject("cliente",clienteDao.buscarSessaoCliente());
		anuncioView.addObject("maisvendido", anuncioMaisVendido);
		return anuncioView;
	}
	
	@RequestMapping("/produtos/{id}")
	public ModelAndView verAnuncio(@PathVariable String id) {
		Optional<AnuncioModel> anuncio = anuncioDao.buscarAnuncio(id);
		if(anuncio.isPresent()) {
			ModelAndView anuncioView =  new ModelAndView("produtos");
			anuncioView.addObject("anuncio", anuncio.get());
			anuncioView.addObject("cliente",clienteDao.buscarSessaoCliente());
			return anuncioView;
		}
		ModelAndView anuncioView =  new ModelAndView("produtos");
		anuncioView.addObject("cliente",clienteDao.buscarSessaoCliente());
		return anuncioView;
	}
	
	@PostMapping("/pesquisarProdutos")
	public ModelAndView pesquisarProdutos(@RequestParam String nome) {
		ModelAndView view = new ModelAndView("pesquisa");
		List<AnuncioModel> anuncios = anuncioDao.buscarAnuncioLike(nome);
		view.addObject("pesquisa",nome);
		view.addObject("cliente",clienteDao.buscarSessaoCliente());
		view.addObject("anuncios",anuncios);
		return view;
	}
}
