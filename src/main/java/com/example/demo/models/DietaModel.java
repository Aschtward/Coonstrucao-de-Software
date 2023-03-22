package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "TB_DIETA")
public class DietaModel {
  @Id @GeneratedValue(strategy = GenerationType.AUTO) protected Long id;

  @NotBlank @Column protected String name;

  @NotBlank @Column protected String content;

  public Long getId() { return id; }

  public void setId(Long id) { this.id = id; }

  public String getName() { return name; }

  public void setName(String name) { this.name = name; }

  public String getContent() { return content; }

  public void setContent(String content) { this.content = content; }
}
