package com.epam.tat19.exception;

public class IlluminanceTooMuchException extends Exception {

	private String message;

	public IlluminanceTooMuchException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
