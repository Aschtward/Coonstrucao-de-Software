package com.example.demo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.RoleModel;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, UUID>{

}
