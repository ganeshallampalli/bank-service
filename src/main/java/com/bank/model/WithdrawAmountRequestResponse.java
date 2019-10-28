package com.bank.model;

public interface WithdrawAmountRequestResponse {

	public class WithdrawAmountRequest {
		private String userId;

		private Double amount;

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public Double getAmount() {
			return amount;
		}

		public void setAmount(Double amount) {
			this.amount = amount;
		}

		@Override
		public String toString() {
			return "WithdrawAmountRequest [userId=" + userId + ", amount=" + amount + "]";
		}

	}
	
	public class WithdrawAmountResponse {
		
	}
}
