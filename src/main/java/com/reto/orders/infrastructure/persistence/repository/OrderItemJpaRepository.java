package com.reto.orders.infrastructure.persistence.repository;

import com.reto.orders.infrastructure.persistence.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemJpaRepository extends JpaRepository<OrderItemEntity,Long> {
}
