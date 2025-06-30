package com.reto.orders.application.usecase;

import com.reto.orders.domain.exception.*;
import com.reto.orders.domain.model.Order;
import com.reto.orders.domain.model.OrderItem;
import com.reto.orders.domain.model.Product;
import com.reto.orders.domain.port.out.OrderRepositoryPort;
import com.reto.orders.domain.port.out.ProductRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderUseCaseTest {

    @Mock
    private OrderRepositoryPort orderRepositoryPort;
    @Mock
    private ProductRepositoryPort productRepositoryPort;
    @InjectMocks
    private OrderUseCase orderUseCase;

    private Order testOrder;
    private Product testProduct;
    private OrderItem testOrderItem;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testProduct = new Product();
        testProduct.setId(1L);
        testProduct.setName("Laptop Lenovo");
        testProduct.setPrice(new BigDecimal("1500.00"));
        testProduct.setAvailableQuantity(10);

        testOrderItem = new OrderItem();
        testOrderItem.setId(1L);
        testOrderItem.setProduct(testProduct);
        testOrderItem.setQuantity(2);
        testOrderItem.setLineTotal(testProduct.getPrice().multiply(BigDecimal.valueOf(2)));

        testOrder = new Order();
        testOrder.setId(1L);
        testOrder.setCustomerName("Juan Pérez");
        testOrder.setTotal(testOrderItem.getLineTotal());
        testOrder.setItems(new ArrayList<>(List.of(testOrderItem)));
    }

    @Test
    void updateQuantity_SuccessfulUpdate() {
        // Arrange
        when(orderRepositoryPort.findById(1L)).thenReturn(Optional.of(testOrder));
        when(productRepositoryPort.findById(1L)).thenReturn(Optional.of(testProduct));

        // Act
        orderUseCase.updateProductQuantityInOrder(1L, 1L, 3);

        // Assert
        assertEquals(3, testOrderItem.getQuantity());
        assertEquals(new BigDecimal("4500.00"), testOrderItem.getLineTotal());
        assertEquals(new BigDecimal("4500.00"), testOrder.getTotal());
        verify(orderRepositoryPort).save(testOrder);
    }

    @Test
    void updateQuantity_RemoveItemWhenZero() {
        // Arrange
        when(orderRepositoryPort.findById(1L)).thenReturn(Optional.of(testOrder));
        when(productRepositoryPort.findById(1L)).thenReturn(Optional.of(testProduct));

        // Act
        orderUseCase.updateProductQuantityInOrder(1L, 1L, 0);

        // Assert
        assertTrue(testOrder.getItems().isEmpty());
        assertEquals(BigDecimal.ZERO, testOrder.getTotal());
        verify(orderRepositoryPort).save(testOrder);
    }

    @Test
    void updateQuantity_OrderNotFound() {
        when(orderRepositoryPort.findById(2L)).thenReturn(Optional.empty());
        var ex = assertThrows(OrderNotFoundException.class,
                () -> orderUseCase.updateProductQuantityInOrder(2L, 1L, 2));
        assertTrue(ex.getMessage().contains("2"));
    }

    @Test
    void updateQuantity_ProductNotFoundInOrder() {
        when(orderRepositoryPort.findById(1L)).thenReturn(Optional.of(testOrder));
        var ex = assertThrows(ProductNotFoundExceptionInOrder.class,
                () -> orderUseCase.updateProductQuantityInOrder(1L, 99L, 2));
        assertTrue(ex.getMessage().contains("99"));
    }

    @Test
    void updateQuantity_InvalidQuantity() {
        when(orderRepositoryPort.findById(1L)).thenReturn(Optional.of(testOrder));
        var ex = assertThrows(InvalidQuantityException.class,
                () -> orderUseCase.updateProductQuantityInOrder(1L, 1L, -5));
        assertTrue(ex.getMessage().toLowerCase().contains("no puede ser menor que cero"));
    }

    @Test
    void updateQuantity_ExceededQuantity() {
        when(orderRepositoryPort.findById(1L)).thenReturn(Optional.of(testOrder));
        testProduct.setAvailableQuantity(1);
        when(productRepositoryPort.findById(1L)).thenReturn(Optional.of(testProduct));

        var ex = assertThrows(ExceededQuantityException.class,
                () -> orderUseCase.updateProductQuantityInOrder(1L, 1L, 5));
        assertTrue(ex.getMessage().toLowerCase().contains("excede"));
    }

    @Test
    void updateQuantity_UpdateFailed() {
        when(orderRepositoryPort.findById(1L)).thenReturn(Optional.of(testOrder));
        when(productRepositoryPort.findById(1L)).thenReturn(Optional.of(testProduct));
        doThrow(new RuntimeException("DB Error")).when(orderRepositoryPort).save(any());

        var ex = assertThrows(UpdateFailedException.class,
                () -> orderUseCase.updateProductQuantityInOrder(1L, 1L, 2));
        assertTrue(ex.getMessage().toLowerCase().contains("error"));
    }

}
