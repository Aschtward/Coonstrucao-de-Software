package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.ClientDAO;
import com.example.demo.dao.ProdutoCompradoDAO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;

@Controller
public class ClientController {

	@Autowired
	ClientDAO cDao;
	@Autowired
	ProdutoCompradoDAO produtoCompradoDao;
	@Autowired
	TokenController tController;

	@PostMapping(value = "/createaccount")
	public String adicionarCliente(@Valid @RequestParam String name,@Valid @Email @RequestParam String email,
			@Valid @Min(6) @RequestParam String password, Model model) {
		cDao.adicionarCliente(name, email, password);
		return "/login";
	}
	

	@RequestMapping(value = "/confirmAccount", method = { RequestMethod.GET, RequestMethod.POST })
	public String confirmarConta(@RequestParam("token") String confirmationToken) {
		cDao.confirmarEmail(confirmationToken);
		return "/confirm";
	}
	
	
	@GetMapping(value = "/createaccount")
	public String getPage() {
		return "/criar_conta";
	}
	
	@RequestMapping("/loginPage")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/errorToken")
	public String tokenInvalido() {
		return "/tokenInvalido";
	}
	
	@GetMapping("/forgotPassword")
	public String forgotPassworView() {
		return "/recover_password";
	}
	
	@GetMapping("/deleteAccount")
	public String excluirConta() {
		tController.removerToken();
		cDao.excluirConta();
		return "/login";
	}
}
