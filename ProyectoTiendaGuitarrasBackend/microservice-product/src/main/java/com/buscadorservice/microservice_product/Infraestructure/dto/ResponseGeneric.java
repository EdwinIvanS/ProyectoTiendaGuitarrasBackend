package com.buscadorservice.microservice_product.Infraestructure.dto;
import lombok.*;

@Data
@AllArgsConstructor
public class ResponseGeneric<T> {
    private String message;
    private T data;
}
