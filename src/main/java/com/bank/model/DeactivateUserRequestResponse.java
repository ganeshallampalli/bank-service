package com.bank.model;

public interface DeactivateUserRequestResponse {

	public class DeactivateUserRequest {

		private String userId;

		private String role;

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		@Override
		public String toString() {
			return "DeactivateUserRequest [userId=" + userId + ", role=" + role + "]";
		}

	}

	public class DeactivateUserResponse {

	}
}
