package com.bank.entity.service;

import com.bank.entity.UserAccount;
import com.bank.model.BaseRequestResponse.BaseResponse;
import com.bank.model.CreateUserAccountRequestResponse.CreateUserAccountRequest;
import com.bank.model.CreateUserAccountRequestResponse.CreateUserAccountResponse;
import com.bank.model.DeactivateUserRequestResponse.DeactivateUserRequest;
import com.bank.model.DeactivateUserRequestResponse.DeactivateUserResponse;

public interface IUserAccountDAOService {

	BaseResponse<CreateUserAccountResponse> createUserDetails(CreateUserAccountRequest createUserAccountRequest);
	
	UserAccount findByUserId(String userId);

	BaseResponse<DeactivateUserResponse> deActivateAccount(DeactivateUserRequest deactivateUserRequest);
}
