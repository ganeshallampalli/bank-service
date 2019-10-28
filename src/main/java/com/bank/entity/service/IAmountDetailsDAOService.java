package com.bank.entity.service;

import com.bank.entity.AmountDetails;
import com.bank.model.BaseRequestResponse.BaseResponse;
import com.bank.model.WithdrawAmountRequestResponse.WithdrawAmountResponse;

public interface IAmountDetailsDAOService {

	AmountDetails getBalanceInfo(String userId);
	
	void creditMoney(String userId, Double amount);
	
	BaseResponse<WithdrawAmountResponse> withdrawMoney(String userId, Double amount);
}
