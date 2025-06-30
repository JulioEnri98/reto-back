package com.reto.orders.application.usecase;

import com.reto.orders.domain.exception.*;
import com.reto.orders.domain.model.Order;
import com.reto.orders.domain.model.OrderItem;
import com.reto.orders.domain.model.Product;
import com.reto.orders.domain.port.in.OrderUseCasePort;
import com.reto.orders.domain.port.out.OrderRepositoryPort;
import com.reto.orders.domain.port.out.ProductRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static com.reto.orders.domain.constants.Messages.*;

@Service
public class OrderUseCase implements OrderUseCasePort {

    private final OrderRepositoryPort orderRepositoryPort;
    private final ProductRepositoryPort productRepositoryPort;

    public OrderUseCase(OrderRepositoryPort orderRepositoryPort, ProductRepositoryPort productRepositoryPort) {
        this.orderRepositoryPort = orderRepositoryPort;
        this.productRepositoryPort = productRepositoryPort;
    }

    /**
     * Updates the quantity of a product in an order.
     *
     * @param orderId      the ID of the order
     * @param productId    the ID of the product
     * @param newQuantity  the new quantity to set
     * @throws OrderNotFoundException if the order does not exist
     * @throws ProductNotFoundExceptionInOrder if the product is not found in the order
     * @throws InvalidQuantityException if the new quantity is negative
     * @throws ExceededQuantityException if the new quantity exceeds available stock
     * @throws UpdateFailedException if there is an error during update
     */
    @Override
    @Transactional
    public void updateProductQuantityInOrder(long orderId, Long productId, int newQuantity) {
        // Validate order existence
        Order order = orderRepositoryPort.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(String.format(ORDER_NOT_FOUND, orderId)));

        // Validate product existence in order and update quantity
        OrderItem item = order.getItems().stream()
                .filter(i -> i.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundExceptionInOrder(String.format(PRODUCT_NOT_FOUND_IN_ORDER, productId)));

        // Validate new quantity
        if (newQuantity < 0)
            throw new InvalidQuantityException(INVALID_QUANTITY);

        // Validate product availability
        Product product = productRepositoryPort.findById(productId)
                .orElseThrow(() -> new ProductNotFoundExceptionInOrder(String.format(PRODUCT_NOT_FOUND, productId)));

        // Check if new quantity exceeds available stock
        if (newQuantity > product.getAvailableQuantity())
            throw new ExceededQuantityException(String.format(EXCEEDED_QUANTITY, newQuantity, product.getAvailableQuantity()));

        // Update the quantity in the order item
        if (newQuantity == 0) {
            order.getItems().remove(item);
        } else {
            item.setQuantity(newQuantity);
            item.setLineTotal(product.getPrice().multiply(BigDecimal.valueOf(newQuantity)));
        }

        // Update the order's total amount
        BigDecimal newOrderTotal = order.getItems().stream()
                .map(OrderItem::getLineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotal(newOrderTotal);

        // Save the updated order
        try {
            orderRepositoryPort.save(order);
        } catch (Exception e) {
            throw new UpdateFailedException(UPDATE_FAILED, e);
        }
    }
}
