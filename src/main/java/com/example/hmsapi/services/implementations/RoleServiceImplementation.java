package com.example.hmsapi.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hmsapi.models.Role;
import com.example.hmsapi.repositories.RoleRepository;
import com.example.hmsapi.services.RoleService;

@Service
public class RoleServiceImplementation implements RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	@Override
	public Role saveRole(Role role) {
		// TODO Auto-generated method stub
		return roleRepository.save(role);
	}

}
