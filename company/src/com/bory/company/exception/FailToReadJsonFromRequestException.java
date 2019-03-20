package com.bory.company.exception;

public class FailToReadJsonFromRequestException extends RuntimeException {
	private static final long serialVersionUID = 6703256960467753048L;

	public FailToReadJsonFromRequestException() {
		super();
	}

	public FailToReadJsonFromRequestException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public FailToReadJsonFromRequestException(String arg0) {
		super(arg0);
	}

	public FailToReadJsonFromRequestException(Throwable arg0) {
		super(arg0);
	}
	
}
