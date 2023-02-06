package com.example.demo.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.ClientDAO;


@Controller
@RequestMapping("/changePassword")
public class ChangePasswordView {
	
	@Autowired
	ClientDAO cDao;

	@RequestMapping(method = RequestMethod.GET)
	public String trocarSenhaView(@Param(value = "token") String token, Model model) {
		model.addAttribute("token", token);
		return "trocarSenha";
	}
	
	@RequestMapping(method  = RequestMethod.POST)
	public String trocarSenha( @RequestParam String password,@Param(value = "token") String token) {
		cDao.changePassword(password, token);
		return "/login";
	}
}
