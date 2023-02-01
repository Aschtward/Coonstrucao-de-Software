package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.enums.RoleName;
import com.example.demo.models.RoleModel;
import com.example.demo.repository.RoleRepository;

@Component
public class RolesDAO {
	
	final RoleRepository roleRepository;

	public RolesDAO(RoleRepository roleRepository) {
		super();
		this.roleRepository = roleRepository;
		RoleModel client = new RoleModel();
		client.setRoleName(RoleName.ROLE_CLIENTE);
		RoleModel anunciante = new RoleModel();
		anunciante.setRoleName(RoleName.ROLE_ANUNCIANTE);
		roleRepository.save(client);
		roleRepository.save(anunciante);
	}
	
	public List<RoleModel> buscarAllRoles(){
		return roleRepository.findAll();
	}
	public List<RoleModel> buscarRoles(RoleName roleName){
		return roleRepository.findByRoleName(roleName);
	}
	
	
}
