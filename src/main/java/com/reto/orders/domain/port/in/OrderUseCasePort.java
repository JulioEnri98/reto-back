package com.reto.orders.domain.port.in;

public interface OrderUseCasePort {
    void updateProductQuantityInOrder(long orderId, Long productId, int newQuantity);
}
