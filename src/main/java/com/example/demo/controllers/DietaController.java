package com.example.demo.controllers;

import com.example.demo.dao.DietaDAO;
import com.example.demo.models.DietaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class DietaController {
  @Autowired
  DietaDAO dDao;

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

  @GetMapping("/editDiet/{id}")
  public ModelAndView editarDietaView(@PathVariable String id) {
    DietaModel dModel = dDao.getDieta(Long.parseLong(id));
    ModelAndView editDietView = new ModelAndView("editar_dieta");
    editDietView.addObject("dieta", dModel);
    return editDietView;
  }

  @PostMapping("/editDiet/{id}")
  public RedirectView editarDieta(@PathVariable String id,
      @RequestParam("name") String name,
      @RequestParam("diet") String diet) {
    dDao.editarDieta(Long.parseLong(id), name, diet);
    return new RedirectView("../perfil");
  }

  @PostMapping("/removeDiet/{id}")
  public RedirectView removerDieta(@PathVariable String id) {
    dDao.removerDieta(Long.parseLong(id));
    return new RedirectView("../perfil");
  }
}
