package com.example.demo.dao;

import com.example.demo.models.DietaModel;
import com.example.demo.repository.DietaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DietaDAO {
  @Autowired DietaRepository dRepo;

  public void criarDieta(String nome, String content) {
    DietaModel dModel = new DietaModel();
    dModel.setName(nome);
    dModel.setContent(content);
    dRepo.save(dModel);
  }

  public DietaModel getDieta(Long id) { return dRepo.getReferenceById(id); }

  public void editarDieta(Long id, String name, String content) {
    DietaModel dModel = getDieta(id);
    dModel.setName(name);
    dModel.setContent(content);
    dRepo.save(dModel);
  }

  public void removerDieta(Long id) { dRepo.delete(getDieta(id)); }
}
