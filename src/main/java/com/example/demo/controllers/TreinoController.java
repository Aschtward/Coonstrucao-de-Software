package com.example.demo.controllers;

import com.example.demo.dao.TreinoDAO;
import com.example.demo.models.TreinoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class TreinoController {
  @Autowired
  TreinoDAO tDao;

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

  @GetMapping("/editarTreino/{id}")
  public ModelAndView editarTreinoView(@PathVariable String id) {
    TreinoModel tModel = tDao.getTreino(Long.parseLong(id));
    ModelAndView editarTreinoView = new ModelAndView("editar_treino");
    editarTreinoView.addObject("treino", tModel);
    return editarTreinoView;
  }

  @PostMapping("/editarTreino/{id}")
  public RedirectView editarTreino(@PathVariable String id,
      @RequestParam("name") String name,
      @RequestParam("content") String content) {
    tDao.editarTreino(Long.parseLong(id), name, content);
    return new RedirectView("../perfil");
  }

  @PostMapping("/removerTreino/{id}")
  public RedirectView removerTreino(@PathVariable String id) {
    tDao.removerTreino(Long.parseLong(id));
    return new RedirectView("../perfil");
  }
}
