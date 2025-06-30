package com.reto.orders.infrastructure.api.exception;

import com.reto.orders.domain.exception.*;
import com.reto.orders.infrastructure.api.response.ApiResponse;
import com.reto.orders.infrastructure.config.ResponseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.reto.orders.domain.constants.Constants.CODE_BAD_REQUEST;
import static com.reto.orders.domain.constants.Constants.CODE_INFO_ERROR;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ApiResponse> handleOrderNotFound(OrderNotFoundException ex) {
        return ResponseUtils.buildErrorResponse(
                CODE_INFO_ERROR,
                ex.getMessage(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(ProductNotFoundExceptionInOrder.class)
    public ResponseEntity<ApiResponse> handleProductNotFound(ProductNotFoundExceptionInOrder ex) {
        return ResponseUtils.buildErrorResponse(
                CODE_BAD_REQUEST,
                ex.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(InvalidQuantityException.class)
    public ResponseEntity<ApiResponse> handleInvalidQuantity(InvalidQuantityException ex) {
        return ResponseUtils.buildErrorResponse(
                CODE_BAD_REQUEST,
                ex.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(ExceededQuantityException.class)
    public ResponseEntity<ApiResponse> handleExceededQuantity(ExceededQuantityException ex) {
        return ResponseUtils.buildErrorResponse(
                CODE_BAD_REQUEST,
                ex.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(UpdateFailedException.class)
    public ResponseEntity<ApiResponse> handleUpdateFailed(UpdateFailedException ex) {
        return ResponseUtils.buildErrorResponse(
                CODE_INFO_ERROR,
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGenericException(Exception ex) {
        return ResponseUtils.buildErrorResponse(
                CODE_INFO_ERROR,
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
