package com.bank.entity.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bank.entity.AmountDetails;

@Repository
public interface AmountDetailsRepository extends CrudRepository<AmountDetails, Integer>{

	@SuppressWarnings("unchecked")
	AmountDetails save(AmountDetails accountDetails);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE amount_details SET amount = amount + ?2  WHERE user_id = ?1")
	void depositAmount(String userId, Double amount);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE amount_details SET amount = amount - ?2  WHERE user_id = ?1")
	void withdrawAmount(String userId, Double amount);
	
	AmountDetails findByUserId(String userId); 
}
