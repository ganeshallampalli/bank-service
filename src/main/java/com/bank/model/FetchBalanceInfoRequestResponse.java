package com.bank.model;

public interface FetchBalanceInfoRequestResponse {

	public class FetchBalanceInfoRequest {
		private String userId;

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		@Override
		public String toString() {
			return "FetchBalanceInfoRequest [userId=" + userId + "]";
		}

	}

	public class FetchBalanceInfoResponse {
		private String userId;

		private Double balance;

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public Double getBalance() {
			return balance;
		}

		public void setBalance(Double balance) {
			this.balance = balance;
		}

		@Override
		public String toString() {
			return "FetchBalanceInfoResponse [userId=" + userId + ", balance=" + balance + "]";
		}

	}
}
