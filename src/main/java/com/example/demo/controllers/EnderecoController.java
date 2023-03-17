package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.dao.EnderecoDAO;

@Controller
public class EnderecoController {
	
	@Autowired
	EnderecoDAO enderecoDao;
	@PostMapping("/mudarEndereco")
	@ResponseBody
	public RedirectView adicionarEndereco(@RequestParam  String nome, @RequestParam  String cidade,@RequestParam  String rua,@RequestParam  String bairro,@RequestParam  String numero,@RequestParam  String id) {
		enderecoDao.alterarEndereco(bairro, rua, cidade, numero, nome, id);
		return new RedirectView("perfil");
	}
}
