package com.yuan.exception;

import org.springframework.util.Assert;

import com.yuan.utils.Status;

/**
 * 状态码异常。
 */
public class StatusCodeException extends RuntimeException {

	private final Status statusCode;

	protected StatusCodeException() {
		this.statusCode = Status.error("DEFAULT");
	}

	public StatusCodeException(Status statusCode) {
		super(assertNotNull(statusCode));
		this.statusCode = statusCode;
	}

	public StatusCodeException(String message, Status statusCode) {
		super(message + " " + assertNotNull(statusCode));
		this.statusCode = statusCode;
	}

	public StatusCodeException(String message, Throwable cause, Status statusCode) {
		super(message + " " + assertNotNull(statusCode), cause);
		this.statusCode = statusCode;
	}

	public StatusCodeException(Throwable cause, Status statusCode) {
		super(cause.getMessage() + " " + assertNotNull(statusCode), cause);
		this.statusCode = statusCode;
	}

	private static String assertNotNull(Status statusCode) {
		Assert.notNull(statusCode, "statusCode is required; it must not be null");
		return statusCode.toString();
	}

	public Status getStatusCode() {
		return statusCode;
	}
}
