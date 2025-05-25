package com.operadorservice.microservice_orders.Infraestructure.dto;

import lombok.Data;

@Data
public class ProductResponseWrapper {
    private String message;
    private ProductResponse data;
}
