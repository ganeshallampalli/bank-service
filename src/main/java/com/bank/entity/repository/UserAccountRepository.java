package com.bank.entity.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bank.entity.UserAccount;

@Repository
public interface UserAccountRepository extends CrudRepository<UserAccount, Integer> {

	@SuppressWarnings("unchecked")
	UserAccount save(UserAccount user);
	
	UserAccount findByUserId(String userId);
	
}
