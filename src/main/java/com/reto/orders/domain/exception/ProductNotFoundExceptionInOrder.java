package com.reto.orders.domain.exception;

public class ProductNotFoundExceptionInOrder extends RuntimeException {
    public ProductNotFoundExceptionInOrder(String message) {
        super(message);
    }
}
