package com.reto.orders.domain.port.out;

import com.reto.orders.domain.model.Product;

import java.util.Optional;

public interface ProductRepositoryPort {
    Optional<Product> findById(Long id);
}
