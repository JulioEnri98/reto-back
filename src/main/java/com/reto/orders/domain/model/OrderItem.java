package com.reto.orders.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItem {
    private Long id;
    private Product product;
    private int quantity;
    private BigDecimal lineTotal;
}
