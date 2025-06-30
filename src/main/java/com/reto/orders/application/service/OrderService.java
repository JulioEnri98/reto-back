package com.reto.orders.application.service;

import com.reto.orders.application.usecase.OrderUseCase;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderUseCase orderUseCase;

    public OrderService(OrderUseCase orderUseCase) {
        this.orderUseCase = orderUseCase;
    }

    public void updateProductQuantityInOrder(long orderId, Long productId, int newQuantity) {
        orderUseCase.updateProductQuantityInOrder(orderId, productId, newQuantity);
    }

}
