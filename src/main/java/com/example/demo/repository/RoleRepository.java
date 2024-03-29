package com.example.demo.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.enums.RoleName;
import com.example.demo.models.RoleModel;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, UUID>{
	
	List<RoleModel> findByRoleName(RoleName roleName);
}
