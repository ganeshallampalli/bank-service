package com.bank.model;

public interface DepositAmountRequestResponse {

	public class DepositAmountRequest {

		private String userId;

		private Double depositAmount;

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public Double getDepositAmount() {
			return depositAmount;
		}

		public void setDepositAmount(Double depositAmount) {
			this.depositAmount = depositAmount;
		}

		@Override
		public String toString() {
			return "DepositAmountRequest [userId=" + userId + ", depositAmount=" + depositAmount + "]";
		}

	}

	public class DepositAmountResponse {
	}
}
