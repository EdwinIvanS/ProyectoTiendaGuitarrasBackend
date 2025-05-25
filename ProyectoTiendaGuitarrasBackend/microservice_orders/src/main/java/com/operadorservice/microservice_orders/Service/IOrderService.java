package com.operadorservice.microservice_orders.Service;

import com.operadorservice.microservice_orders.Infraestructure.dto.*;
import java.util.List;

public interface IOrderService {
    List<OrderResponseDto> getAllOrders();

    OrderResponseDto findById(String id);

    OrderResponseDto createOrder(OrderRequestDto request);

    OrderResponseDto update(String id, OrderRequestDto dto);

    void delete(String id);

    List<OrderResponseDto> findByCustomerName(String customerName);
}
