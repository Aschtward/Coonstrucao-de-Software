package com.example.demo.repository;

import com.example.demo.models.Atividades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtividadesRepository extends JpaRepository<Atividades, Long> {
}
