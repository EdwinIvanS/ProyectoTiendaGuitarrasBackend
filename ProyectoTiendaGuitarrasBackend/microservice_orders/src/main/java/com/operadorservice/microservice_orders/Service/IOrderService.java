package com.operadorservice.microservice_orders.Service;

import com.operadorservice.microservice_orders.Infraestructure.dto.*;
import com.operadorservice.microservice_orders.Infraestructure.model.Order;
import java.util.List;

public interface IOrderService {
    List<OrderResponseDto> getAllOrders();

    OrderResponseDto findById(String id);

    List<OrderResponseDto> findByProductId(Long productId);

    Order createOrder(OrderRequestDto request);

    OrderResponseDto update(String id, OrderRequestDto dto);

    void delete(String id);
}
