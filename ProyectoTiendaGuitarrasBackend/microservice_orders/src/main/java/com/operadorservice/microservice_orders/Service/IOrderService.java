package com.operadorservice.microservice_orders.Service;

import com.operadorservice.microservice_orders.Infraestructure.model.Order;
import com.operadorservice.microservice_orders.Infraestructure.dto.*;
import java.util.List;

public interface IOrderService {
    Order createOrder(OrderRequest request);
    List<Order> getAllOrders();
}
