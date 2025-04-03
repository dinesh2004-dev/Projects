package com.farmSystem.exceptions;

public class EquipmentNotFound extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EquipmentNotFound() {
		super();
	}
	
	public EquipmentNotFound(String message) {
		super(message);
	}

}
