package com.example.demo.repository;

import com.example.demo.models.DietaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DietaRepository extends JpaRepository<DietaModel, Long> {
}
