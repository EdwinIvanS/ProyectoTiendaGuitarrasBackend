package com.operadorservice.microservice_orders.Infraestructure.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponseDto {
    private Long productId;
    private String productName;
    private double price;
    private int quantity;
    private double total;
    private String imageUrl;
}
