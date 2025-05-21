package com.buscadorservice.microservice_product.Infraestructure.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
public class GuitarRequestDto {
    @NotBlank(message = "El nombre es obligatorio")
    private String name;
    @NotBlank(message = "La imagen es obligatoria")
    private String image;
    @NotBlank(message = "La descripcion es obligatoria")
    private String description;
    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor que cero")
    private double price;
    @NotNull(message = "El stock es obligatorio")
    @Positive(message = "El stock debe ser mayor que cero")
    private Integer stock;
}
