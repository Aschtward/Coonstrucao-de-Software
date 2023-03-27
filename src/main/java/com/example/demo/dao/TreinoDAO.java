package com.example.demo.dao;

import com.example.demo.models.ClienteModels;
import com.example.demo.models.TreinoModel;
import com.example.demo.repository.TreinoRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TreinoDAO {
  @Autowired TreinoRepository tRepo;
  @Autowired ClientDAO cDao;

  public void criarTreino(String name, String content) {
    TreinoModel tModel = new TreinoModel();
    tModel.setName(name);
    tModel.setContent(content);
    tRepo.save(tModel);
    ClienteModels cModel = cDao.buscarSessaoCliente();
    if (cModel.getTreinoModels() != null) {
      cModel.getTreinoModels().add(tModel);
      cDao.salvarCliente(cModel);
    } else {
      cModel.setTreinoModels(new ArrayList<TreinoModel>());
      cModel.getTreinoModels().add(tModel);
      cDao.salvarCliente(cModel);
    }
  }

  public TreinoModel getTreino(Long id) { return tRepo.getReferenceById(id); }

  public void editarTreino(Long id, String name, String content) {
    TreinoModel tModel = getTreino(id);
    tModel.setName(name);
    tModel.setContent(content);
    tRepo.save(tModel);
  }

  public void removerTreino(Long id) { tRepo.delete(getTreino(id)); }
}
