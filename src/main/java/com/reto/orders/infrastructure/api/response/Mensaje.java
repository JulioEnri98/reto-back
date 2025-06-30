package com.reto.orders.infrastructure.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Mensaje {
  String codigo;
  String tipo;
  String message;
}
