package com.operadorservice.microservice_orders.Repository;

import com.operadorservice.microservice_orders.Infraestructure.model.Order;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Order, String> {
    List<Order> findByProductId(Long productId);
}
