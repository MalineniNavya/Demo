package com.hcl.ecommerence.exception;

public class ProductAlreadyExistException extends Exception{
private static final long serialVersionUID = 1L;
	
	
	String message;


	public ProductAlreadyExistException(String message) {
		super(message);
		this.message = message;
	}


	public ProductAlreadyExistException() {
		super();
	}


}
