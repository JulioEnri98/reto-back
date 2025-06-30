package com.reto.orders.infrastructure.persistence.mapper;

import com.reto.orders.domain.model.Product;
import com.reto.orders.infrastructure.persistence.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductEntityMapper {
    Product toModel(ProductEntity entity);
}
