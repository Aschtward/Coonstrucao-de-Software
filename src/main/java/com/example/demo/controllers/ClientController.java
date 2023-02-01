package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.ClientDAO;

@Controller
public class ClientController {

	@Autowired
	ClientDAO cDao;

	@GetMapping(value = "/createaccount")
	public String getPage() {
		return "/create_account";
	}

	@PostMapping(value = "/createaccount")
	public String adicionarCliente(@RequestParam String name, @RequestParam String email,
			@RequestParam String password) {
		System.out.println(email + password);
		cDao.adicionarCliente(name, email, password);
		return "/login";
	}

	@RequestMapping(value = "/confirmAccount", method = { RequestMethod.GET, RequestMethod.POST })
	public String confirmarConta(@RequestParam("token") String confirmationToken) {
		cDao.confirmarEmail(confirmationToken);
		return "/confirm";
	}

	@RequestMapping("/loginPage")
	public String login() {
		return "login";
	}

}
