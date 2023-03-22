package com.example.demo.controllers;

import com.example.demo.dao.ClientDAO;
import com.example.demo.dao.EmailDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SupportController {
  @Autowired ClientDAO cDao;
  @Autowired EmailDAO eDao;

  @GetMapping("/suporte")
  public String SuporteView() {
    return "/suporte";
  }

  @PostMapping("/suporte")
  public String enviarEmail(@RequestParam("subject") String assunto,
                            @RequestParam("content") String conteudo) {
    String nomeRemetente = cDao.buscarSessaoCliente().getName();
    String assunto_real =
        "Usuário:" + cDao.buscarSessaoCliente().getId().toString() + " -> " +
        assunto;
    String email = "applyandgrowth@gmail.com";
    eDao.sendSupportEmail(nomeRemetente, email, email, assunto_real, conteudo);
    return "/email_enviado";
  }
}
