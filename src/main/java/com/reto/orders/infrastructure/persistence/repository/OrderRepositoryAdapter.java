package com.reto.orders.infrastructure.persistence.repository;

import com.reto.orders.domain.model.Order;
import com.reto.orders.domain.port.out.OrderRepositoryPort;
import com.reto.orders.infrastructure.persistence.mapper.OrderEntityMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrderRepositoryAdapter implements OrderRepositoryPort {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderEntityMapper orderEntityMapper;

    public OrderRepositoryAdapter(OrderJpaRepository orderJpaRepository, OrderEntityMapper orderEntityMapper) {
        this.orderJpaRepository = orderJpaRepository;
        this.orderEntityMapper = orderEntityMapper;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderJpaRepository.findById(id)
                .map(orderEntityMapper::toModel);
    }

    @Override
    public void save(Order order) {
        orderEntityMapper.toModel(
                orderJpaRepository.save(orderEntityMapper.toEntity(order))
        );
    }
}
