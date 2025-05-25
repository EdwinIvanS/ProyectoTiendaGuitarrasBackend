package com.operadorservice.microservice_orders.Infraestructure.dto;

import java.util.List;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto {
    @NotBlank(message = "El nombre del cliente es obligatorio")
    private String customerName;
    @NotEmpty(message = "La lista de items no puede estar vacía")
    private List<@Valid OrderItemRequestDto> items;
}