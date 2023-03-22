package com.example.demo.repository;

import com.example.demo.models.ExercicioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExercicioRepository
    extends JpaRepository<ExercicioModel, Long> {}
