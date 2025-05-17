package com.buscadorservice.microservice_product.Infraestructure.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class GuitarResponseDto {
    private Long id;
    private String name;
    private String image;
    private String description;
    private double price;

}
