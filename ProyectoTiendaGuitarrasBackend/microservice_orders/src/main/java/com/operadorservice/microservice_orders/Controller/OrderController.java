package com.operadorservice.microservice_orders.Controller;

import org.springframework.web.bind.annotation.*;
import com.operadorservice.microservice_orders.Service.OrderService;
import com.operadorservice.microservice_orders.Infraestructure.model.*;
import com.operadorservice.microservice_orders.Infraestructure.dto.*;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;
    
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }
    
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody @Valid OrderRequest request){
        try {
            Order order = orderService.createOrder(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(order);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno: " + e.getMessage());
        }
    }
    
    @GetMapping
    public ResponseEntity<?> getAllOrders(){
        try {
            List<Order> orders = orderService.getAllOrders();
            if(orders.isEmpty()) return ResponseEntity.noContent().build();            
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno: " + e.getMessage());
        }        
    }
}
