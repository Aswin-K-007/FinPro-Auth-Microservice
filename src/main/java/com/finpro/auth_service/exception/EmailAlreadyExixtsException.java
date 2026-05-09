package com.finpro.auth_service.exception;

public class EmailAlreadyExixtsException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public EmailAlreadyExixtsException(String message) {
        super(message);
    }

}
