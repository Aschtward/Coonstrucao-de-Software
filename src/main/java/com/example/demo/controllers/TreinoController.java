package com.example.demo.controllers;

import com.example.demo.dao.TreinoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TreinoController {
  @Autowired TreinoDAO tDao;

  @GetMapping("/treinos")
  public String exercicioView() {
    return "/treinos";
  }

  @GetMapping("/criarTreinos")
  public String criarTreinosView() {
    return "/criarTreinos";
  }

  @PostMapping("/criarTreinos")
  public String criarTreinos() {
    tDao.criarTreino();
    return "/treinos";
  }
}
