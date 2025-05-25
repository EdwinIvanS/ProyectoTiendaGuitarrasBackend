package com.operadorservice.microservice_orders.Infraestructure.mapper;

import java.util.UUID;
import com.operadorservice.microservice_orders.Infraestructure.dto.*;
import com.operadorservice.microservice_orders.Infraestructure.model.*;

public class OrderItemMapper {

    public static OrderItemResponseDto toDto(OrderItem item) {
        return new OrderItemResponseDto(
                item.getProductId(),
                item.getProductName(),
                item.getPrice(),
                item.getQuantity(),
                item.getTotal(),
                item.getImageUrl());
    }

    public static OrderItem fromRequestDto(OrderItemRequestDto dto, Order order, ProductResponse product) {
        return OrderItem.builder()
                .id(UUID.randomUUID().toString())
                .order(order)
                .productId(dto.getProductId())
                .productName(product.getName())
                .price(product.getPrice())
                .quantity(dto.getQuantity())
                .total(product.getPrice() * dto.getQuantity())
                .imageUrl(product.getImage())
                .build();
    }
}
