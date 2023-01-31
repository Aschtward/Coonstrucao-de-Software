package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.ClientDAO;

@Controller
public class ClientController {
	
	@Autowired
	ClientDAO cDao;
	
	@GetMapping(value  = "/createaccount")
	public String getPage(  String name,  String email,  String password) {
		return "/create_account";
	}
	
	@PostMapping(value  = "/createaccount")
	public String adicionarCliente(  @RequestParam String name, @RequestParam String email,@RequestParam String password) {
		cDao.adicionarCliente(name, email, password);
		return "/index";
	}

}
