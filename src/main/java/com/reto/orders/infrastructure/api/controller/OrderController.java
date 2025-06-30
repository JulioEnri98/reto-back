package com.reto.orders.infrastructure.api.controller;

import com.reto.orders.application.service.OrderService;
import com.reto.orders.infrastructure.api.response.ApiResponse;
import com.reto.orders.infrastructure.config.ResponseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.reto.orders.domain.constants.Constants.CODE_INFO_SUCCESS;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Endpoint to update the quantity of a product in an order.
     *
     * @param orderId    the ID of the order
     * @param productId  the ID of the product
     * @param newQuantity the new quantity for the product
     * @return a response indicating success or failure
     */
    @PutMapping("/{orderId}/products/{productId}")
    public ResponseEntity<ApiResponse> updateProductQuantity(@PathVariable Long orderId,
                                                             @PathVariable Long productId,
                                                             @RequestParam int newQuantity) {
        orderService.updateProductQuantityInOrder(orderId, productId, newQuantity);
        return ResponseUtils.buildSuccessResponse(
                null,
                CODE_INFO_SUCCESS,
                HttpStatus.OK
        );
    }
}
