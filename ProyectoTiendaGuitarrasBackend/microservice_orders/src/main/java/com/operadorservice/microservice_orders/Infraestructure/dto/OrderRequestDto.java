package com.operadorservice.microservice_orders.Infraestructure.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto {

    @NotNull(message = "El ID del producto es obligatorio")
    private Long productId;

    @NotNull(message = "El nombre del producto es obligatorio")
    private String productName;

    @Positive(message = "El precio debe ser mayor que cero")
    private double price;

    @Min(value = 1, message = "La cantidad debe ser mayor a cero")
    private int quantity;

    @Positive(message = "El total debe ser mayor que cero")
    private double total;

    @Size(max = 100, message = "La URL de la imagen no debe superar los 100 caracteres")
    private String imageUrl;
}
