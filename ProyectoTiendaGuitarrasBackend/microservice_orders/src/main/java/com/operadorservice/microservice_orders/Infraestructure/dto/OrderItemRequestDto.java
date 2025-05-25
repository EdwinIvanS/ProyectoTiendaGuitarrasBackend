package com.operadorservice.microservice_orders.Infraestructure.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequestDto {
    @NotNull(message = "El ID del producto no puede ser nulo")
    private Long productId;

    @NotBlank(message = "El nombre del producto no puede estar vacío")
    private String productName;

    @Positive(message = "El precio debe ser un número positivo")
    private double price;

    @Min(value = 1, message = "La cantidad mínima debe ser 1")
    private int quantity;

    private String imageUrl;
}
