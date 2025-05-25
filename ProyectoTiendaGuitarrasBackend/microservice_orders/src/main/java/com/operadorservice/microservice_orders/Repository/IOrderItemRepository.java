package com.operadorservice.microservice_orders.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.operadorservice.microservice_orders.Infraestructure.model.OrderItem;

public interface IOrderItemRepository extends JpaRepository<OrderItem, String>{
    List<OrderItem> findByProductId(Long productId);
}
