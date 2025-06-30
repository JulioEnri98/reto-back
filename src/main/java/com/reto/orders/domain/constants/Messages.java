package com.reto.orders.domain.constants;

public final class Messages {

    private Messages() {
        // Prevent instantiation
    }

    public static final String ORDER_NOT_FOUND = "El pedido con ID %d no existe.";
    public static final String PRODUCT_NOT_FOUND_IN_ORDER = "El producto con ID %d no existe en el pedido.";
    public static final String PRODUCT_NOT_FOUND = "El producto con ID %d no existe en el catálogo.";
    public static final String INVALID_QUANTITY = "La cantidad no puede ser menor que cero.";
    public static final String EXCEEDED_QUANTITY = "La cantidad solicitada (%d) excede el stock disponible (%d).";
    public static final String UPDATE_FAILED = "Ocurrió un error inesperado al actualizar el pedido.";
}
