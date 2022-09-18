package com.management.shipping.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Some constraints are violated ...")
public class ConstraintsViolationException extends Exception {

	private static final long serialVersionUID = 1L;

	public ConstraintsViolationException(String message)
    {
        super(message);
    }
	
}
