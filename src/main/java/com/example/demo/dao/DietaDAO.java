package com.example.demo.dao;

import com.example.demo.models.ClienteModels;
import com.example.demo.models.DietaModel;
import com.example.demo.repository.DietaRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DietaDAO {
  @Autowired
  DietaRepository dRepo;
  @Autowired
  ClientDAO cDao;

  public void criarDieta(String nome, String content) {
    DietaModel dModel = new DietaModel();
    dModel.setName(nome);
    dModel.setContent(content);
    dRepo.save(dModel);
    ClienteModels cModel = cDao.buscarSessaoCliente();
    if (cModel.getTreinoModels() != null) {
      cModel.getDietaModels().add(dModel);
      cDao.salvarCliente(cModel);
    } else {
      cModel.setDietaModels(new ArrayList<DietaModel>());
      cModel.getDietaModels().add(dModel);
      cDao.salvarCliente(cModel);
    }
  }

  public DietaModel getDieta(Long id) {
    return dRepo.getReferenceById(id);
  }

  public void editarDieta(Long id, String name, String content) {
    DietaModel dModel = getDieta(id);
    dModel.setName(name);
    dModel.setContent(content);
    dRepo.save(dModel);
  }

  public void removerDieta(Long id) {
    ClienteModels cModel = cDao.buscarSessaoCliente();
    cModel.getDietaModels().remove(getDieta(id));
    cDao.salvarCliente(cModel);
    dRepo.delete(getDieta(id));
  }
}
