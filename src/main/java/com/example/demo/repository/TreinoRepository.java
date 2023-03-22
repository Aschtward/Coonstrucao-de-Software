package com.example.demo.repository;

import com.example.demo.models.TreinoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreinoRepository extends JpaRepository<TreinoModel, Long> {}
