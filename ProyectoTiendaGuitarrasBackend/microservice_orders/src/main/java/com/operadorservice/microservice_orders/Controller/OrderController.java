package com.operadorservice.microservice_orders.Controller;

import org.springframework.web.bind.annotation.*;
import com.operadorservice.microservice_orders.Service.IOrderService;
import com.operadorservice.microservice_orders.Infraestructure.model.*;
import com.operadorservice.microservice_orders.Infraestructure.dto.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

import java.util.List;

import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;

@RestController
@RequestMapping("/api/pedidos")
@Validated
public class OrderController {
    private final IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        List<OrderResponseDto> orders = orderService.getAllOrders();
        return ResponseEntity.ok(new ResponseGeneric<>("Productos encontrados", orders));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable String id) {
        OrderResponseDto order = orderService.findById(id);
        return ResponseEntity.ok(new ResponseGeneric<>("Orden encontrado", order));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ResponseGeneric<List<OrderResponseDto>>> getOrdersByProductId(@PathVariable @Positive(message = "El ID debe ser mayor que cero") Long productId) {
        List<OrderResponseDto> orders = orderService.findByProductId(productId);
        if (orders.isEmpty()) {
            return ResponseEntity.ok(new ResponseGeneric<>("Producto no existe en las ordenes", orders));
        }
        return ResponseEntity.ok(new ResponseGeneric<>("Producto contenido en las ordenes", orders));
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody @Valid OrderRequestDto request) {
        Order order = orderService.createOrder(request);
        return ResponseEntity.ok(new ResponseGeneric<>("Orden creada", order));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDto> updateOrder(@PathVariable String id, @RequestBody OrderRequestDto dto) {
        OrderResponseDto updatedOrder = orderService.update(id, dto);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
