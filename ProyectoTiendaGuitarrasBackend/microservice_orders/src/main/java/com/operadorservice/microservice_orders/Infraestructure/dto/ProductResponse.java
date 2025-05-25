package com.operadorservice.microservice_orders.Infraestructure.dto;

import lombok.*;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private String image;
    private String description;
    private double price;
    private int stock;
}
