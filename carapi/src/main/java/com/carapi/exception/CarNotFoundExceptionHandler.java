package com.carapi.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CarNotFoundExceptionHandler {
	
	@ExceptionHandler(CarNotFoundException.class)
	public ResponseEntity<Object> handlerResourceNotFoundException(CarNotFoundException e){
		Map<String,Object> body = new HashMap<>();
		body.put("message", e.getMessage());
		return new ResponseEntity<>(body,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException(Exception e){
		Map<String,Object> body = new HashMap<>();
		body.put("message", "An error occured");
		return new ResponseEntity<>(body,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
