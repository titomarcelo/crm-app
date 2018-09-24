package com.bangalo.commons.domain;

public class DomainException extends Exception {

	private static final long serialVersionUID = 8868225299505336370L;

	public DomainException(final String message) {
		super(message);
	}

	public DomainException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
