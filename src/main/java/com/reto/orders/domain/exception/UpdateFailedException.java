package com.reto.orders.domain.exception;

public class UpdateFailedException extends RuntimeException {
    public UpdateFailedException(String message) {
        super(message);
    }

    public UpdateFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
