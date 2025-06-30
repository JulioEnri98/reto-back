package com.reto.orders.infrastructure.persistence.mapper;

import com.reto.orders.domain.model.Order;
import com.reto.orders.infrastructure.persistence.entity.OrderEntity;
import com.reto.orders.infrastructure.persistence.entity.OrderItemEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OrderEntityMapper {
    @Mapping(target = "items", source = "orderItems")
    Order toModel(OrderEntity entity);

    @Mapping(target = "orderItems", source = "items")
    OrderEntity toEntity(Order model);

    @AfterMapping
    default void afterMapping(Order model, @MappingTarget OrderEntity entity) {
        if (entity.getOrderItems() != null) {
            entity.getOrderItems().removeIf(i -> model.getItems().stream()
                    .noneMatch(mi -> mi.getProduct().getId().equals(i.getProduct().getId())));
            for (OrderItemEntity item : entity.getOrderItems()) {
                item.setOrder(entity);
            }
        }
    }
}
