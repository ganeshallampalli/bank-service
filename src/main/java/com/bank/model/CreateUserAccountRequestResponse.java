package com.bank.model;

public interface CreateUserAccountRequestResponse {

	public class CreateUserAccountRequest {

		private String firstName;

		private String lastName;

		private String address;

		private String govtIdNumber;

		private String govtIdType;

		private String role;

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getGovtIdNumber() {
			return govtIdNumber;
		}

		public void setGovtIdNumber(String govtIdNumber) {
			this.govtIdNumber = govtIdNumber;
		}

		public String getGovtIdType() {
			return govtIdType;
		}

		public void setGovtIdType(String govtIdType) {
			this.govtIdType = govtIdType;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		@Override
		public String toString() {
			return "CreateUserAccountRequest [firstName=" + firstName + ", lastName=" + lastName + ", address="
					+ address + ", govtIdNumber=" + govtIdNumber + ", govtIdType=" + govtIdType + ", role=" + role
					+ "]";
		}

	}

	public class CreateUserAccountResponse {

		private String userId;

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		@Override
		public String toString() {
			return "CreateUserAccountResponse [userId=" + userId + "]";
		}

	}

}
