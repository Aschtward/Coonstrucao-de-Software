package com.example.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EnderecoController {
	
	@PostMapping("/adicionarEndereco")
	public HttpStatus adicionarEndereco() {
		return HttpStatus.OK;
	}
}
