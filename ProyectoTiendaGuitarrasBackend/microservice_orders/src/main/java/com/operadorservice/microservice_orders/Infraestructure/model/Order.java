package com.operadorservice.microservice_orders.Infraestructure.model;

import java.time.LocalDateTime;
import java.util.UUID;
import jakarta.persistence.*; 
import lombok.*;

@Entity
@Table(name = "orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue
    private UUID id;

    private Long productId;

    private String productName;

    private double price;

    private int quantity;

    private double total;

    private String imageUrl;

    private LocalDateTime createdAt;

}
