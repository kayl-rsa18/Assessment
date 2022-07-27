package com.assessment.library.model;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class ErrorDetails {
	
	private String errorMessage;
	private String errorDescription;
	private Date timestamp;
	private HttpStatus httpStatus;
	
	public ErrorDetails(String errorMessage, String errorDescription, Date timestamp, HttpStatus httpStatus) {
		super();
		this.errorMessage = errorMessage;
		this.errorDescription = errorDescription;
		this.timestamp = timestamp;
		this.httpStatus = httpStatus;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorDescription() {
		return errorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

}
