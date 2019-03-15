package com.bory.company.exception;

public class IllegalParameterException extends RuntimeException {
	private static final long serialVersionUID = 6703256960467753048L;

	public IllegalParameterException() {
		super();
	}

	public IllegalParameterException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public IllegalParameterException(String arg0) {
		super(arg0);
	}

	public IllegalParameterException(Throwable arg0) {
		super(arg0);
	}
	
}
