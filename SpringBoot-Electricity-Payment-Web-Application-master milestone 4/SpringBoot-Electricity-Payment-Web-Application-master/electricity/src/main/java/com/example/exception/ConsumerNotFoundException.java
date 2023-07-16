package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ConsumerNotFoundException extends RuntimeException{
	
	String resourceName;
	String fieldName;
	 
	String fieldValueVariable;

	
	public ConsumerNotFoundException(String resourceName, String fieldName, String fieldValueVariable) {
	    
		  super(String.format("%s not found with %s: %s",fieldName,  fieldName, fieldValueVariable));
		  
		  //super(message);
		  
		  this.resourceName = resourceName;
		  this.fieldName = fieldName;
		  this.fieldValueVariable = fieldValueVariable;

}

}