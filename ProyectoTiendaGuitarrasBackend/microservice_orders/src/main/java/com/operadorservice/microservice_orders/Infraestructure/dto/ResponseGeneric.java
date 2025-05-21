package com.operadorservice.microservice_orders.Infraestructure.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class ResponseGeneric<T> {
    private String message;
    private T data;
}
