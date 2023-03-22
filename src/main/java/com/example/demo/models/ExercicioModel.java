package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "TB_EXERCICIOS")
public class ExercicioModel {
  @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;

  @NotBlank @Column private String name;

  @NotBlank @Column private byte series;

  @NotBlank @Column private byte repetitions;

  public String getName() { return name; }

  public void setName(String name) { this.name = name; }

  public byte getSeries() { return series; }

  public void setSeries(byte series) { this.series = series; }

  public byte getReps() { return repetitions; }

  public void setReps(byte repetitions) { this.repetitions = repetitions; }
}
