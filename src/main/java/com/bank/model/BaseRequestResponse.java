package com.bank.model;

/**
 * 
 * @author ganeshallampalli
 *
 */

public interface BaseRequestResponse {

	public class BaseRequest {
		/**
		 * Basic Request Template
		 */
	}

	/**
	 * Basic Response Response of all Response models.
	 */
	public class BaseResponse<T> {

		private String code;

		private String message;

		private T responseData;

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public T getResponseData() {
			return responseData;
		}

		public void setResponseData(T responseData) {
			this.responseData = responseData;
		}

	}
}
