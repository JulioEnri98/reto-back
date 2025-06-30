package com.reto.orders.infrastructure.config;

import com.reto.orders.domain.constants.Constants;
import com.reto.orders.infrastructure.api.response.ApiResponse;
import com.reto.orders.infrastructure.api.response.ApiResponseMeta;
import com.reto.orders.infrastructure.api.response.Mensaje;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.Collections;
import java.util.List;
import java.util.Map;


public class ResponseUtils {

    private ResponseUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static ResponseEntity<ApiResponse> buildSuccessResponse(Object data, String codeInfo, HttpStatus  httpStatus) {
        ApiResponse response = new ApiResponse();
        ApiResponseMeta meta = new ApiResponseMeta();
        meta.setResult(Constants.RESULTADO_OK);
        meta.setMensajes(Collections.singletonList(new Mensaje(codeInfo, "SUCCESS","")));
        meta.setAtributos(Collections.emptyMap());
        if (data instanceof List<?> dataList) {
            meta.setCantidadRegistros(dataList.size());
            meta.setCantidadRegistrosTotal(dataList.size());
        } else {
            meta.setCantidadRegistros(1);
            meta.setCantidadRegistrosTotal(1);
        }
        response.setMeta(meta);
        response.setData(data);
        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<ApiResponse> buildErrorResponse(String code, String message, HttpStatus status) {
        ApiResponse response = new ApiResponse();
        ApiResponseMeta meta = new ApiResponseMeta();
        meta.setResult(Constants.RESULTADO_ERROR);
        meta.setMensajes(List.of(new Mensaje(code, "ERROR", message)));
        meta.setCantidadRegistros(0);
        meta.setCantidadRegistrosTotal(0);
        meta.setAtributos(Collections.emptyMap());
        response.setMeta(meta);
        response.setData(null);
        return new ResponseEntity<>(response, status);
    }

    public static ApiResponse buildPagedResponseFromPage(Page<?> page, String codeInfo) {
        ApiResponse response = new ApiResponse();
        ApiResponseMeta meta = new ApiResponseMeta();

        meta.setResult(Constants.RESULTADO_OK);
        meta.setMensajes(List.of(new Mensaje(codeInfo, "SUCCESS", "")));
        meta.setCantidadRegistros(page.getNumberOfElements());
        meta.setCantidadRegistrosTotal((int) page.getTotalElements());
        meta.setAtributos(Map.of(
                "page", page.getNumber() + 1,
                "size", page.getSize(),
                "totalPages", page.getTotalPages(),
                "hasNext", page.hasNext(),
                "hasPrevious", page.hasPrevious()
        ));

        response.setMeta(meta);
        response.setData(page.getContent());

        return response;
    }

}
