package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.dao.ClientDAO;
import com.example.demo.dao.TokenDAO;
import com.example.demo.repository.TokenRepository;

@Controller
public class TokenController {
	@Autowired
	TokenDAO tDao;
	@Autowired
	TokenRepository tRepo;
	@Autowired
	ClientDAO cDao;

	public void removerToken() {
		tDao.removerToken(tRepo.findById(cDao.buscarSessaoCliente().getId()).get());
	}
}
