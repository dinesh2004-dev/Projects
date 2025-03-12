package com.farmSystem.exceptions;

public class UserNotLender extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotLender() {
		super();
	}
	
	public UserNotLender(String message) {
		super(message);
	}

}
