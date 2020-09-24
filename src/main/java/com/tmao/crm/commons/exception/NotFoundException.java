package com.tmao.crm.commons.exception;

public class NotFoundException extends Exception {

    private static final long serialVersionUID = -8714565545024259747L;

    public NotFoundException(final String message) {
        super(message);
    }

    public NotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
