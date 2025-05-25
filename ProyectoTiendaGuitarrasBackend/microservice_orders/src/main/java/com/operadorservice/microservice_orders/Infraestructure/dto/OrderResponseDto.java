package com.operadorservice.microservice_orders.Infraestructure.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
    private String id;
    private String customerName;
    private LocalDateTime createdAt;
    private String status;
    private List<OrderItemResponseDto> items;
}