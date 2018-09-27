package com.rich.rest.exception;

public class DataNotFoundException extends RuntimeException {

	private String message;

	public DataNotFoundException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
