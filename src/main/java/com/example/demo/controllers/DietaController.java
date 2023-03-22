package com.example.demo.controllers;

import com.example.demo.dao.DietaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DietaController {
  @Autowired DietaDAO dDao;

  @GetMapping("/newDiet")
  public String dietaView() {
    return "/dietas";
  }

  @GetMapping({"/newDiet", "/editDiet"})
  public String escreverDietaView() {
    return "/escrever_dieta";
  }

  @PostMapping("/newDiet")
  public String criarDieta(@RequestParam("name") String name,
                           @RequestParam("content") String content) {
    dDao.criarDieta(name, content);
    return "/dietas";
  }

  @PostMapping("/editDiet")
  public String editarDieta(@RequestParam("id") Long id,
                            @RequestParam("name") String name,
                            @RequestParam("content") String content) {
    dDao.editarDieta(id, name, content);
    return "/dietas";
  }

  @RequestMapping("/removeDiet")
  public String removerDieta(@RequestParam("id") Long id) {
    dDao.removerDieta(id);
    return "/dietas";
  }
}
