package com.bitter.data.exception;

public class BDataException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public BDataException(String message) {
        super();
        this.message = message;
    }
}
