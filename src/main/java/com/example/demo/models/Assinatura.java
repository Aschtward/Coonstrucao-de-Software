package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "TB_SUBSCRIPTION")
public class Assinatura {
  @Id @GeneratedValue(strategy = GenerationType.AUTO) private Long id;

  @Column private LocalDate dataExpira;

  public Long getId() { return id; }

  public void setId(Long id) { this.id = id; }

  public LocalDate getDataExpira() { return dataExpira; }

  public Assinatura(@NotBlank LocalDate dataExpira) {
    this.dataExpira = dataExpira;
  }

  public Assinatura() {}

  public void setDataExpira(LocalDate dataExpira) {
    this.dataExpira = dataExpira;
  }
}
