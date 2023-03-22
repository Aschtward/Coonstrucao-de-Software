package com.example.demo.dao;

import com.example.demo.models.ExercicioModel;
import com.example.demo.repository.ExercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExercicioDAO {
  @Autowired ExercicioRepository eRepo;

  public void criarExercicio(String name, byte series, byte repetitions) {
    ExercicioModel eModel = new ExercicioModel();
    eModel.setName(name);
    eModel.setSeries(series);
    eModel.setReps(repetitions);
    eRepo.save(eModel);
  }

  public ExercicioModel getExercicio(Long id) {
    return eRepo.getReferenceById(id);
  }

  public void editarExercicio(Long id, String name, byte series,
                              byte repetitions) {
    ExercicioModel eModel = getExercicio(id);
    eModel.setName(name);
    eModel.setSeries(series);
    eModel.setReps(repetitions);
    eRepo.save(eModel);
  }

  public void removerExercicio(Long id) { eRepo.delete(getExercicio(id)); }
}
