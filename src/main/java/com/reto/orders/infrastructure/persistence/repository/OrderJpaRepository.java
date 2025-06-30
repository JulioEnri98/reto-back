package com.reto.orders.infrastructure.persistence.repository;

import com.reto.orders.infrastructure.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<OrderEntity,Long> {
}
