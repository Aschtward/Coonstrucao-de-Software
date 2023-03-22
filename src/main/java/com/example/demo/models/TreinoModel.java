package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "TB_TREINOS")
public class TreinoModel {
  @Id @GeneratedValue(strategy = GenerationType.AUTO) private Long id;

  @OneToMany
  @JoinTable(name = "TB_TREINO_EXERCICIO")
  private List<ExercicioModel> eModels;

  public Long getId() { return id; }

  public List<ExercicioModel> getExercicios() { return eModels; }

  public void setExercicios(List<ExercicioModel> eModels) {
    this.eModels = eModels;
  }

  public ExercicioModel getExercicio(int index) { return eModels.get(index); }

  public void addExercicio(ExercicioModel eModel) { eModels.add(eModel); }

  public void removeExercicio(ExercicioModel eModel) { eModels.remove(eModel); }
}
