package com.example.demo.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClienteView {

	@GetMapping(value = "/createaccount")
	public String getPage() {
		return "/create_account";
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
}
