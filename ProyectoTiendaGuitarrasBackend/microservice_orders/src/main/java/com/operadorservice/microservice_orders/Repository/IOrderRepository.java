package com.operadorservice.microservice_orders.Repository;
import com.operadorservice.microservice_orders.Infraestructure.model.Order;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Order, UUID>{}
