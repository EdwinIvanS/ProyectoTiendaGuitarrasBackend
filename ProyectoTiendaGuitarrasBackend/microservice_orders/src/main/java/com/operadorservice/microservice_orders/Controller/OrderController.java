package com.operadorservice.microservice_orders.Controller;

import org.springframework.web.bind.annotation.*;
import com.operadorservice.microservice_orders.Service.IOrderService;
import com.operadorservice.microservice_orders.Infraestructure.dto.*;
import jakarta.validation.Valid;

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
        if (orders.isEmpty())
            return ResponseEntity.ok(new ResponseGeneric<>("No hay ordenes registradas", orders));

        return ResponseEntity.ok(new ResponseGeneric<>("Ordenes encontradas", orders));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable String id) {
        OrderResponseDto order = orderService.findById(id);
        return ResponseEntity.ok(new ResponseGeneric<>("Orden encontrada", order));
    }

    @GetMapping("/byCustomer")
    public ResponseEntity<List<OrderResponseDto>> getOrdersByCustomerName(
            @RequestParam String customerName) {
        List<OrderResponseDto> orders = orderService.findByCustomerName(customerName);
        return ResponseEntity.ok(orders);
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody @Valid OrderRequestDto request) {
        OrderResponseDto order = orderService.createOrder(request);
        return ResponseEntity.ok(new ResponseGeneric<>("Orden creada", order));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDto> updateOrder(@PathVariable String id,
            @RequestBody @Valid OrderRequestDto dto) {
        OrderResponseDto updatedOrder = orderService.update(id, dto);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
