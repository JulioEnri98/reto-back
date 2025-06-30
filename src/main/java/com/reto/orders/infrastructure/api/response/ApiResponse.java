package com.reto.orders.infrastructure.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ApiResponse {
    @JsonProperty("meta")
    private ApiResponseMeta meta;
    @JsonProperty("data")
    private Object data;
}
