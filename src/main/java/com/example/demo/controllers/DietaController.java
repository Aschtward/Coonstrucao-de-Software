package com.example.demo.controllers;

import com.example.demo.dao.DietaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class DietaController {
  @Autowired DietaDAO dDao;

  @GetMapping("/newDiet")
  public String escreverDietaView() {
    return "/criar_dieta";
  }

  @PostMapping("/newDiet")
  public RedirectView criarDieta(@RequestParam("name") String name,
                                 @RequestParam("diet") String diet) {
    dDao.criarDieta(name, diet);
    return new RedirectView("perfil");
  }

  @GetMapping("/editDiet")
  public String editarDietaView() {
    return "/editar_dieta";
  }

  @PostMapping("/editDiet")
  public RedirectView editarDieta(@RequestParam("id") Long id,
                                  @RequestParam("name") String name,
                                  @RequestParam("diet") String diet) {
    dDao.editarDieta(id, name, diet);
    return new RedirectView("perfil");
  }

  @RequestMapping("/removeDiet")
  public RedirectView removerDieta(@RequestParam("id") Long id) {
    dDao.removerDieta(id);
    return new RedirectView("perfil");
  }
}
