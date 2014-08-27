package com.johnkuper.exceptions;

public class OwnMapperException extends Exception {

	static final long serialVersionUID = 1;

	public OwnMapperException() {
	}

	public OwnMapperException(String message) {
		super(message);
	}

	public OwnMapperException(Throwable cause) {
		super(cause);
	}

	public OwnMapperException(String message, Throwable cause) {
		super(message, cause);

	}
}
