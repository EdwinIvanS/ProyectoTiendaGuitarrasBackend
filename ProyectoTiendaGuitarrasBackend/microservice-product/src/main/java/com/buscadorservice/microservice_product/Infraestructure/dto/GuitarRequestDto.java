package com.buscadorservice.microservice_product.Infraestructure.dto;

import lombok.*;

@Data
public class GuitarRequestDto {
    private String name;
    private String image;
    private String description;
    private double price;
}
