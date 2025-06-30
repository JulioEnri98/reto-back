package com.reto.orders.infrastructure.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApiResponseMeta {
  @JsonProperty("result")
  private String result;
  @JsonProperty("cantidadRegistros")
  private Integer cantidadRegistros;
  @JsonProperty("cantidadRegistrosTotal")
  private Integer cantidadRegistrosTotal;
  @JsonProperty("mensajes")
  private List<Mensaje> mensajes;
  @JsonProperty("atributos")
  private Map<String, Object> atributos;

}
