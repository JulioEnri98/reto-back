package com.reto.orders.infrastructure.persistence.repository;

import com.reto.orders.domain.model.Product;
import com.reto.orders.domain.port.out.ProductRepositoryPort;
import com.reto.orders.infrastructure.persistence.mapper.ProductEntityMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final ProductJpaRepository productJpaRepository;
    private final ProductEntityMapper productEntityMapper;

    public ProductRepositoryAdapter(ProductJpaRepository productJpaRepository, ProductEntityMapper productEntityMapper) {
        this.productJpaRepository = productJpaRepository;
        this.productEntityMapper = productEntityMapper;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productJpaRepository.findById(id)
                .map(productEntityMapper::toModel);
    }

}
