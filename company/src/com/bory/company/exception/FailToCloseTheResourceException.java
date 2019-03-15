package com.bory.company.exception;

public class FailToCloseTheResourceException extends RuntimeException {
	
	private static final long serialVersionUID = 7449624979645832548L;

	public FailToCloseTheResourceException() {
		super();
	}

	public FailToCloseTheResourceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public FailToCloseTheResourceException(String arg0) {
		super(arg0);
	}

	public FailToCloseTheResourceException(Throwable arg0) {
		super(arg0);
	}
	
}
