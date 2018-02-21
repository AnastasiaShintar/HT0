package com.epam.tat19.exception;

public class SpaceUsageTooMuchException extends Exception {

	private String message;

	public SpaceUsageTooMuchException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
