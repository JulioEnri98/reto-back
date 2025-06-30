package com.reto.orders.domain.exception;

public class ExceededQuantityException extends RuntimeException {
    public ExceededQuantityException(String message) {
        super(message);
    }
}
