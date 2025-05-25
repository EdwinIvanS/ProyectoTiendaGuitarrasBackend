package com.operadorservice.microservice_orders.Infraestructure.mapper;

import java.util.*;
import java.util.stream.*;
import com.operadorservice.microservice_orders.Infraestructure.dto.*;
import com.operadorservice.microservice_orders.Infraestructure.model.Order;

public class OrderMapper {

    public static OrderResponseDto toDto(Order order) {
        List<OrderItemResponseDto> itemsDto = order.getItems().stream()
                .map(OrderItemMapper::toDto)
                .collect(Collectors.toList());

        return new OrderResponseDto(
                order.getId(),
                order.getCustomerName(),
                order.getCreatedAt(),
                order.getStatus(),
                itemsDto);
    }
}