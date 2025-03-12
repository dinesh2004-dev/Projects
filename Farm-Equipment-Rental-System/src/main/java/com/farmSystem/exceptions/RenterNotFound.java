package com.farmSystem.exceptions;

public class RenterNotFound extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RenterNotFound() {
		super();
	}
	
	public RenterNotFound(String message) {
		super(message);
	}

}
