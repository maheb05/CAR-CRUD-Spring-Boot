package com.carapi.exception;

public class CarNotFoundException extends RuntimeException{
	private final String errorCode;
	
	public CarNotFoundException(String notFound,String message) {
		super(message);
		this.errorCode=notFound;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
}
