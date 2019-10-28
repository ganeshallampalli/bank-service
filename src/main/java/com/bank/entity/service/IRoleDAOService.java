package com.bank.entity.service;

import com.bank.entity.Role;

public interface IRoleDAOService {

	Role findByRoleType(String roleType);
}
