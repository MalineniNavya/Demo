package com.hcl.ecommerence.exception;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hcl.ecommerence.ApiStatusCode;

@RestControllerAdvice //=  @ControllerAdvice + @ResponseBody
public class GlobalExceptionHandler {

	@ExceptionHandler(CartSizeExceedException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(CartSizeExceedException ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setStatuscode(ApiStatusCode.NOT_ACCEPTABLE);
		errorResponse.setDateTime(LocalDateTime.now());
		
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.OK);
	}
	
	@ExceptionHandler(NullPointerException.class)
	public String exceptionHandler(NullPointerException ex) {
		return ex.getMessage() ;
	}
	
	@ExceptionHandler(ProductAlreadyExistException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(ProductAlreadyExistException ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setStatuscode(ApiStatusCode.PRODUCT_NOT_FOUND);
		errorResponse.setDateTime(LocalDateTime.now());
		
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.OK);
	}
	
	
	  @ExceptionHandler(ConstraintViolationException.class) 
	  public ResponseEntity<ErrorResponse> exceptionHandler(ConstraintViolationException  ex) {
		  ErrorResponse errorResponse = new ErrorResponse();
	  errorResponse.setMessage(ex.getMessage()); errorResponse.setStatuscode(400);
	  errorResponse.setDateTime(LocalDateTime.now());
	  
	  return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.OK); }
	  
	 }

	
	
	
