package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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

	@PostMapping(value = "/createaccount")
	public String adicionarCliente(@Valid @RequestParam String name,@Valid @Email @RequestParam String email,
			@Valid @Min(6) @RequestParam String password, Model model) {
		cDao.adicionarCliente(name, email, password);
		return "/login";
	}
	
	@GetMapping("/perfil")
	public ModelAndView exibirPerfil() {
		ModelAndView perfil = new ModelAndView("perfil");
		perfil.addObject("cliente", cDao.buscarSessaoCliente());
		perfil.addObject("enderecos", cDao.buscarSessaoCliente().getEndereco());
		perfil.addObject("compras", produtoCompradoDao.buscarPorCliente(cDao.buscarSessaoCliente()));
		return perfil;
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
	@PostMapping("/forgotPassword")
	public String forgotPasswor(@RequestParam String email) {
		cDao.sendRecoveryToken(email);
		return "login";
	}
	
	@PostMapping("/cadastrarEndereco")
	public RedirectView cadastrarEndereco(@RequestParam  String nome, @RequestParam  String cidade,@RequestParam  String rua,@RequestParam  String bairro,@RequestParam  String numero){
		cDao.cadastrarEnderecoCliente(nome, cidade, rua, bairro, numero);
		return new RedirectView("perfil");
	}
	
	@PostMapping("/salvarAlteracoes")
	public ResponseEntity<Void> salvarAlteracoes(@RequestParam String nome, @RequestParam String senhaAntiga, @RequestParam(required = false) String senhaNova, @RequestParam String email) {
		if(cDao.alterarCliente(nome,senhaAntiga,senhaNova,email)) { return ResponseEntity.ok().build();}
		return ResponseEntity.badRequest().build();
	}
	
	
}
