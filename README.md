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
