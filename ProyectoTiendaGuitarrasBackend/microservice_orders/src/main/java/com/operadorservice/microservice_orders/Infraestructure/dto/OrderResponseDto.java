package com.operadorservice.microservice_orders.Infraestructure.dto;

import java.time.LocalDateTime;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
    private String id;

    private Long productId;

    private String productName;

    private double price;

    private int quantity;

    private double total;

    private String imageUrl;

    private LocalDateTime createdAt;
}
