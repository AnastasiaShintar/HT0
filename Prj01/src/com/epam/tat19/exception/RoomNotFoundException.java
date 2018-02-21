package com.epam.tat19.exception;

public class RoomNotFoundException extends Exception {
	
	private String message;

	public RoomNotFoundException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	

}
