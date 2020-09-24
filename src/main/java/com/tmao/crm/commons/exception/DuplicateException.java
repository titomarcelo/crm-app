package com.tmao.crm.commons.exception;

public class DuplicateException extends Exception {

    private static final long serialVersionUID = -8866257380601768436L;

    public DuplicateException(final String message) {
        super(message);
    }

    public DuplicateException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
