package com.springboot.app.carrent.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RentedCarException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public RentedCarException() {
		super(String.format("The car is already rented"));
	}

}
