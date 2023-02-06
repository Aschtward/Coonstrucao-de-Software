package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.enums.RoleName;
import com.example.demo.models.RoleModel;
import com.example.demo.repository.RoleRepository;

@Component
public class RolesDAO {
	
	@Autowired
	RoleRepository roleRepository;
	
	public List<RoleModel> buscarAllRoles(){
		return roleRepository.findAll();
	}
	public List<RoleModel> buscarRoles(RoleName roleName){
		return roleRepository.findByRoleName(roleName);
	}
	
	
}
