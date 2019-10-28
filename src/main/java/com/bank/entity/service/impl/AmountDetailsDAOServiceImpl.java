package com.bank.entity.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.entity.AmountDetails;
import com.bank.entity.repository.AmountDetailsRepository;
import com.bank.entity.service.IAmountDetailsDAOService;
import com.bank.model.BaseRequestResponse.BaseResponse;
import com.bank.model.WithdrawAmountRequestResponse.WithdrawAmountResponse;

/**
 * 
 * @author ganeshallampalli
 *
 */

@Service
public class AmountDetailsDAOServiceImpl implements IAmountDetailsDAOService {

	@Autowired
	private AmountDetailsRepository amountDetailsRepository;

	@Override
	public AmountDetails getBalanceInfo(String userId) {
		return amountDetailsRepository.findByUserId(userId);
	}

	@Override
	@Transactional
	public void creditMoney(String userId, Double amount) {
		amountDetailsRepository.depositAmount(userId, amount);
	}

	@Override
	@Transactional
	public BaseResponse<WithdrawAmountResponse> withdrawMoney(String userId, Double amount) {
		BaseResponse<WithdrawAmountResponse> baseResponse = new BaseResponse<>();
		AmountDetails amountDetails = amountDetailsRepository.findByUserId(userId);
		if (amountDetails.getAmount() > amount) {
			amountDetailsRepository.withdrawAmount(userId, amount);
		} else {
			baseResponse.setCode("50006");
			baseResponse.setMessage("Insufficient Balance.");
			return baseResponse;
		}

		baseResponse.setCode("000");
		baseResponse.setMessage("Withrew Successfully.");
		return baseResponse;
	}

}
