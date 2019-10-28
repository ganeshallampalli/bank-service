package com.bank.entity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.entity.Role;
import com.bank.entity.repository.RoleRepository;
import com.bank.entity.service.IRoleDAOService;

@Service
public class RoleDAOServiceImpl implements IRoleDAOService{

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Role findByRoleType(String roleType) {
		return roleRepository.findByRoleType(roleType);
	}

}
