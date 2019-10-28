package com.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.entity.AmountDetails;
import com.bank.entity.UserAccount;
import com.bank.entity.service.IAmountDetailsDAOService;
import com.bank.entity.service.IUserAccountDAOService;
import com.bank.model.BaseRequestResponse.BaseResponse;
import com.bank.model.CreateUserAccountRequestResponse.CreateUserAccountRequest;
import com.bank.model.CreateUserAccountRequestResponse.CreateUserAccountResponse;
import com.bank.model.DeactivateUserRequestResponse.DeactivateUserRequest;
import com.bank.model.DeactivateUserRequestResponse.DeactivateUserResponse;
import com.bank.model.DepositAmountRequestResponse.DepositAmountRequest;
import com.bank.model.DepositAmountRequestResponse.DepositAmountResponse;
import com.bank.model.FetchBalanceInfoRequestResponse.FetchBalanceInfoRequest;
import com.bank.model.FetchBalanceInfoRequestResponse.FetchBalanceInfoResponse;
import com.bank.model.WithdrawAmountRequestResponse.WithdrawAmountRequest;
import com.bank.model.WithdrawAmountRequestResponse.WithdrawAmountResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class BankController {

	@Autowired
	private IUserAccountDAOService iUserAccountDAOService;

	@Autowired
	private IAmountDetailsDAOService iAmountDetailsDAOService;

	@PostMapping("/v1/balanceInfo")
	@ApiOperation(value = "Create USer Account", response = FetchBalanceInfoResponse.class)
	@ApiResponses({ @ApiResponse(code = 000, message = "Bank details fetched successfully"),
			@ApiResponse(code = 403, message = "Unauthorized Access"),
			@ApiResponse(code = 404, message = "API Not Found"),
			@ApiResponse(code = 50003, message = "User doesn't exist for the given userId."),
			@ApiResponse(code = 50004, message = "Account is deactivated. Please contact your bank.")

	})
	public BaseResponse<FetchBalanceInfoResponse> getBalanceInfo(
			@RequestBody FetchBalanceInfoRequest fetchBalanceInfoRequest) {
		BaseResponse<FetchBalanceInfoResponse> baseResponse = new BaseResponse<>();
		FetchBalanceInfoResponse fetchBalanceInfoResponse = new FetchBalanceInfoResponse();

		String userId = fetchBalanceInfoRequest.getUserId();
		UserAccount userAccount = iUserAccountDAOService.findByUserId(userId);
		if (null == userAccount) {
			baseResponse.setCode("50003");
			baseResponse.setMessage("User doesn't exist for the given userId.");
			return baseResponse;
		}
		if ("INACTIVE".equalsIgnoreCase(userAccount.getStatus())) {
			baseResponse.setCode("50004");
			baseResponse.setMessage("Account is deactivated. Please contact your bank.");
			return baseResponse;
		}
		AmountDetails amountDetails = iAmountDetailsDAOService.getBalanceInfo(userId);
		fetchBalanceInfoResponse.setBalance(amountDetails.getAmount());
		fetchBalanceInfoResponse.setUserId(amountDetails.getUserId());
		baseResponse.setResponseData(fetchBalanceInfoResponse);
		return baseResponse;
	}

	@PostMapping("/v1/createUserAcount")
	@ApiOperation(value = "Create User Account", response = CreateUserAccountResponse.class)
	@ApiResponses({ @ApiResponse(code = 000, message = "Create User account successfully"),
			@ApiResponse(code = 403, message = "Unauthorized Access"),
			@ApiResponse(code = 404, message = "API Not Found"),
			@ApiResponse(code = 50001, message = "Can't create user for the given role."),
			@ApiResponse(code = 50002, message = "Couln't create user. Please try again.")

	})
	public BaseResponse<CreateUserAccountResponse> createUserAccount(
			@RequestBody CreateUserAccountRequest createUserAccountRequest,
			@RequestParam("addRoleType") String addRoleType) {
		BaseResponse<CreateUserAccountResponse> baseResponse = new BaseResponse<>();

		if (!"ADMIN".equals(addRoleType)) {
			baseResponse.setCode("403");
			baseResponse.setMessage("Unauthorized Access");
		} else {
			baseResponse = iUserAccountDAOService.createUserDetails(createUserAccountRequest);
		}

		return baseResponse;
	}

	@PostMapping("/v1/depositAmount")
	@ApiOperation(value = "Deposit Amount", response = DepositAmountResponse.class)
	@ApiResponses({ @ApiResponse(code = 000, message = "Deposited amount successfully"),
			@ApiResponse(code = 403, message = "Unauthorized Access"),
			@ApiResponse(code = 404, message = "API Not Found"),
			@ApiResponse(code = 50003, message = "User doesn't exist for the given userId.")

	})
	public BaseResponse<DepositAmountResponse> getBalanceInfo(@RequestBody DepositAmountRequest depositAmountRequest) {
		BaseResponse<DepositAmountResponse> baseResponse = new BaseResponse<>();
		
		String userId = depositAmountRequest.getUserId();
		UserAccount userAccount = iUserAccountDAOService.findByUserId(userId);
		if (null == iUserAccountDAOService.findByUserId(userId)) {
			baseResponse.setCode("50003");
			baseResponse.setMessage("User doesn't exist for the given userId.");
			return baseResponse;
		}
		
		if ("INACTIVE".equalsIgnoreCase(userAccount.getStatus())) {
			baseResponse.setCode("50004");
			baseResponse.setMessage("Account is deactivated. Please contact your bank.");
			return baseResponse;
		}

		iAmountDetailsDAOService.creditMoney(userId, depositAmountRequest.getDepositAmount());
		baseResponse.setCode("000");
		baseResponse.setMessage("Deposited Amount successfully");

		return baseResponse;
	}

	@PostMapping("/v1/deActivateccount")
	@ApiOperation(value = "DeActivate Account", response = DeactivateUserResponse.class)
	@ApiResponses({ @ApiResponse(code = 000, message = "De-Activated successfully"),
			@ApiResponse(code = 403, message = "Unauthorized Access"),
			@ApiResponse(code = 404, message = "API Not Found"),
			@ApiResponse(code = 50005, message = "Couln't deactivate user account. Please try again.")

	})
	public BaseResponse<DeactivateUserResponse> deActivateAccount(
			@RequestBody DeactivateUserRequest deactivateUserRequest) {
		BaseResponse<DeactivateUserResponse> baseResponse = new BaseResponse<>();

		if (!"ADMIN".equals(deactivateUserRequest.getRole())) {
			baseResponse.setCode("403");
			baseResponse.setMessage("Unauthorized Access");
		} else {
			baseResponse = iUserAccountDAOService.deActivateAccount(deactivateUserRequest);
		}

		return baseResponse;
	}
	
	@PostMapping("/v1/withdrawAmount")
	@ApiOperation(value = "Withdraw Amount", response = DepositAmountResponse.class)
	@ApiResponses({ @ApiResponse(code = 000, message = "Withdrew amount successfully"),
			@ApiResponse(code = 403, message = "Unauthorized Access"),
			@ApiResponse(code = 404, message = "API Not Found"),
			@ApiResponse(code = 50003, message = "User doesn't exist for the given userId."),
			@ApiResponse(code = 50004, message = "Account is deactivated. Please contact your bank."),
			@ApiResponse(code = 50006, message = "Insufficient Balance.")

	})
	public BaseResponse<WithdrawAmountResponse> withDrawAmount(@RequestBody WithdrawAmountRequest withdrawAmountRequest) {
		BaseResponse<WithdrawAmountResponse> baseResponse = new BaseResponse<>();
		
		String userId = withdrawAmountRequest.getUserId();
		UserAccount userAccount = iUserAccountDAOService.findByUserId(userId);
		if (null == iUserAccountDAOService.findByUserId(userId)) {
			baseResponse.setCode("50003");
			baseResponse.setMessage("User doesn't exist for the given userId.");
			return baseResponse;
		}
		
		if ("INACTIVE".equalsIgnoreCase(userAccount.getStatus())) {
			baseResponse.setCode("50004");
			baseResponse.setMessage("Account is deactivated. Please contact your bank.");
			return baseResponse;
		}

		baseResponse =  iAmountDetailsDAOService.withdrawMoney(userId, withdrawAmountRequest.getAmount());

		return baseResponse;
	}


}
