package com.reto.orders.domain.port.out;

import com.reto.orders.domain.model.Order;

import java.util.Optional;

public interface OrderRepositoryPort {
    Optional<Order> findById(Long id);
    void save(Order order);
}
