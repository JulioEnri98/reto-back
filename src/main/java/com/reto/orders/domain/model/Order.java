package com.reto.orders.domain.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class Order {
    private Long id;
    private LocalDateTime orderDate;
    private String customerName;
    private String status;
    private String notes;
    private BigDecimal total;
    private List<OrderItem> items = new ArrayList<>();
}
