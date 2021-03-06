package com.miltos.tutorials.messenger.model;


public class ErrorMessage {
	
	/*
	 * The fields of an error message ...
	 */
	private String errorMessage;
	private int errorCode;
	private String documentation;
	
	/*
	 * The constructors of the class ...
	 */
	public ErrorMessage() {
		//Do nothing ...
	}
	
	public ErrorMessage(String errorMessage, int errorCode, String documentation) {
		super();
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
		this.documentation = documentation;
	}
	
	/*
	 * Getters and Setters ...
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public int getErrorCode() {
		return errorCode;
	}
	
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
	public String getDocumentation() {
		return documentation;
	}
	
	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}
}
