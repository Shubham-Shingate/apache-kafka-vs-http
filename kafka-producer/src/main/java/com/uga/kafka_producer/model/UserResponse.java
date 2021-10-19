package com.uga.kafka_producer.model;

public class UserResponse {

	private String message;

	private Object apiError;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getApiError() {
		return apiError;
	}

	public void setApiError(Object apiError) {
		this.apiError = apiError;
	}

	public UserResponse(String message, Object apiError) {
		this.message = message;
		this.apiError = apiError;
	}

	public UserResponse() {

	}

	@Override
	public String toString() {
		return "UserResponse [message=" + message + ", apiError=" + apiError + "]";
	}

}
