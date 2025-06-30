package com.reto.orders.infrastructure.persistence.repository;

import com.reto.orders.infrastructure.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<ProductEntity,Long> {
}
