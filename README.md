# Reto Backend - Gestión de Pedidos

Este proyecto es una aplicación backend para gestionar pedidos de un sistema de comercio electrónico,  
realizado como reto técnico usando **Spring Boot**, **Java 17+** y **Spring Data JPA**.

---

## **Descripción**

Implemente la lógica para **actualizar la cantidad de un producto en un pedido**,  
manejando correctamente excepciones y transacciones para garantizar la consistencia de la base de datos.

### **Escenario:**

- Cada pedido (`Order`) tiene una lista de productos (`Product`).
- El método principal es `updateProductQuantityInOrder`, el cual:
  - **Actualiza la cantidad** de un producto específico en un pedido.
  - **Valida** existencia de pedido y producto, stock, y cantidad válida.
  - **Lanza excepciones personalizadas** según el caso (pedido no existe, producto no existe en pedido, cantidad inválida, cantidad excedida, error inesperado).
  - **Usa transacciones** para asegurar que los cambios sean consistentes.

---
## Prueba rápida del endpoint (con curl)

Actualiza la cantidad de un producto en una orden:

```bash
curl --location --request PUT 'http://localhost:8085/api/ms-order/orders/5/products/2?newQuantity=31'
----

## **Base de datos y datos de ejemplo**

### **Script para crear la base y tablas**

```sql
CREATE DATABASE ecommerce_order_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(12,2) NOT NULL,
    available_quantity INT NOT NULL
);

CREATE TABLE orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(100) NOT NULL,
    order_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) NOT NULL,
    total DECIMAL(12,2) NOT NULL,
    notes TEXT
);

CREATE TABLE order_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    line_total DECIMAL(12,2) NOT NULL,
    UNIQUE(order_id, product_id),
    FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product(id)
);


INSERT INTO product (name, price, available_quantity) VALUES
  ('Laptop Lenovo', 1500.00, 15),
  ('Mouse Logitech', 45.50, 30),
  ('Teclado Mecánico', 120.99, 25);

INSERT INTO orders (customer_name, status, total, notes) VALUES
  ('Juan Pérez', 'CREATED', 1711.99, 'Primer pedido de prueba');

INSERT INTO order_item (order_id, product_id, quantity, line_total) VALUES
  (1, 1, 1, 1500.00),
  (1, 2, 2, 91.00),
  (1, 3, 1, 120.99);

---
