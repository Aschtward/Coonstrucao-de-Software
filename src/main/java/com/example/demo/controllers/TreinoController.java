package com.example.demo.controllers;

import com.example.demo.dao.TreinoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class TreinoController {
  @Autowired TreinoDAO tDao;

  @GetMapping("/criarTreino")
  public String criarTreinosView() {
    return "/criar_treino";
  }

  @PostMapping("/criarTreino")
  public RedirectView criarTreinos(@RequestParam("name") String name,
                                   @RequestParam("content") String content) {
    tDao.criarTreino(name, content);
    return new RedirectView("perfil");
  }

  @GetMapping("/editarTreino")
  public String editarTreinoView() {
    return "/editar_treino";
  }

  @PostMapping("/editarTreino")
  public RedirectView editarTreino(@RequestParam("id") Long id,
                                   @RequestParam("name") String name,
                                   @RequestParam("content") String content) {
    tDao.editarTreino(id, name, content);
    return new RedirectView("perfil");
  }

  @RequestMapping("/removerTreino")
  public RedirectView removerTreino(@RequestParam("id") Long id) {
    tDao.removerTreino(id);
    return new RedirectView("perfil");
  }
}
