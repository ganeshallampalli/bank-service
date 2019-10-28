package com.bank.entity.service.impl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.entity.AmountDetails;
import com.bank.entity.Role;
import com.bank.entity.UserAccount;
import com.bank.entity.repository.AmountDetailsRepository;
import com.bank.entity.repository.RoleRepository;
import com.bank.entity.repository.UserAccountRepository;
import com.bank.entity.service.IUserAccountDAOService;
import com.bank.model.BaseRequestResponse.BaseResponse;
import com.bank.model.CreateUserAccountRequestResponse.CreateUserAccountRequest;
import com.bank.model.CreateUserAccountRequestResponse.CreateUserAccountResponse;
import com.bank.model.DeactivateUserRequestResponse.DeactivateUserRequest;
import com.bank.model.DeactivateUserRequestResponse.DeactivateUserResponse;

@Service
public class UserAccountDAOServiceImpl implements IUserAccountDAOService {

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private AmountDetailsRepository amountDetailsRepository;

	Random random = new Random();

	@Override
	public BaseResponse<CreateUserAccountResponse> createUserDetails(
			CreateUserAccountRequest createUserAccountRequest) {

		BaseResponse<CreateUserAccountResponse> baseResponse = new BaseResponse<>();
		Role role = roleRepository.findByRoleType(createUserAccountRequest.getRole());
		if (null == role) {
			baseResponse.setCode("5001");
			baseResponse.setMessage("Can't create user for the given role.");
			return baseResponse;
		}
		CreateUserAccountResponse createUserAccountResponse = new CreateUserAccountResponse();
		UserAccount userAccount = new UserAccount();
		userAccount.setFirstName(createUserAccountRequest.getFirstName());
		userAccount.setLastName(createUserAccountRequest.getLastName());
		userAccount.setStatus("ACTIVE");
		userAccount.setUserType(role.getId());
		userAccount.setGovtIdType(createUserAccountRequest.getGovtIdType());
		userAccount.setGovtIdNumber(createUserAccountRequest.getGovtIdNumber());
		userAccount.setUserId("MMT" + random.nextInt(99999999));
		userAccount.setAddress(createUserAccountRequest.getAddress());

		try {
			UserAccount savedUserAccount = userAccountRepository.save(userAccount);
			if (null == savedUserAccount) {
				throw new Exception();
			}
			AmountDetails amountDetails = new AmountDetails();
			amountDetails.setUserId(savedUserAccount.getUserId());
			amountDetails.setAmount(0.00);
			amountDetailsRepository.save(amountDetails);
			createUserAccountResponse.setUserId(savedUserAccount.getUserId());
			baseResponse.setCode("000");
			baseResponse.setMessage("Created User Successfully.");
			baseResponse.setResponseData(createUserAccountResponse);
		} catch (Exception ex) {
			baseResponse.setCode("5002");
			baseResponse.setMessage("Couln't create user. Please try again.");
		}
		return baseResponse;
	}

	@Override
	public UserAccount findByUserId(String userId) {
		return userAccountRepository.findByUserId(userId);
	}

	@Override
	public BaseResponse<DeactivateUserResponse> deActivateAccount(DeactivateUserRequest deactivateUserRequest) {
		BaseResponse<DeactivateUserResponse> baseResponse = new BaseResponse<>();

		UserAccount userAccount = userAccountRepository.findByUserId(deactivateUserRequest.getUserId());
		userAccount.setStatus("INACTIVE");
		try {
			userAccountRepository.save(userAccount);
			baseResponse.setCode("000");
			baseResponse.setMessage("Deactivated successfully.");
		} catch (Exception ex) {
			baseResponse.setCode("5005");
			baseResponse.setMessage("Couln't deactivate user account. Please try again.");
		}
		return baseResponse;
	}

}
