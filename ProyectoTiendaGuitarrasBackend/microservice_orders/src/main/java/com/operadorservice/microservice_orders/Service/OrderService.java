package com.operadorservice.microservice_orders.Service;

import com.operadorservice.microservice_orders.Client.ProductClient;
import com.operadorservice.microservice_orders.Infraestructure.dto.*;
import com.operadorservice.microservice_orders.Infraestructure.exception.ResourceNotFoundException;
import com.operadorservice.microservice_orders.Infraestructure.model.Order;
import com.operadorservice.microservice_orders.Repository.IOrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {
    private IOrderRepository orderRepository;
    private ProductClient productClient;

    public OrderService(IOrderRepository orderRepository, ProductClient productClient) {
        this.orderRepository = orderRepository;
        this.productClient = productClient;
    }

    @Override
    public List<OrderResponseDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(order -> new OrderResponseDto(
                        order.getId(),
                        order.getProductId(),
                        order.getProductName(),
                        order.getPrice(),
                        order.getQuantity(),
                        order.getTotal(),
                        order.getImageUrl(),
                        order.getCreatedAt()))
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponseDto findById(String id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada con id: " + id));
        return new OrderResponseDto(
                order.getId(),
                order.getProductId(),
                order.getProductName(),
                order.getPrice(),
                order.getQuantity(),
                order.getTotal(),
                order.getImageUrl(),
                order.getCreatedAt());
    }

    @Override
    public List<OrderResponseDto> findByProductId(Long productId) {
        List<Order> orders = orderRepository.findByProductId(productId);
        return orders.stream()
                .map(g -> new OrderResponseDto(
                        g.getId(),
                        g.getProductId(),
                        g.getProductName(),
                        g.getPrice(),
                        g.getQuantity(),
                        g.getTotal(),
                        g.getImageUrl(),
                        g.getCreatedAt()))
                .collect(Collectors.toList());
    }

    @Override
    public Order createOrder(OrderRequestDto request) {
        ProductResponse product = productClient.getProductById(request.getProductId());

        double total = BigDecimal.valueOf(product.getPrice()).multiply(BigDecimal.valueOf(request.getQuantity()))
                .doubleValue();

        Order order = Order.builder()
                .id(UUID.randomUUID().toString())
                .productId(request.getProductId())
                .productName(request.getProductName())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .total(total)
                .imageUrl(request.getImageUrl())
                .createdAt(LocalDateTime.now())
                .build();

        return orderRepository.save(order);
    }

    @Override
    public OrderResponseDto update(String id, OrderRequestDto dto) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada con id: " + id));

        order.setProductId(dto.getProductId());
        order.setProductName(dto.getProductName());
        order.setPrice(dto.getPrice());
        order.setQuantity(dto.getQuantity());
        order.setTotal(dto.getPrice() * dto.getQuantity());
        order.setImageUrl(dto.getImageUrl());
        orderRepository.save(order);
        return new OrderResponseDto(order.getId(),
                order.getProductId(),
                order.getProductName(),
                order.getPrice(),
                order.getQuantity(),
                order.getTotal(),
                order.getImageUrl(),
                order.getCreatedAt());
    }

    @Override
    public void delete(String id) {
        if (!orderRepository.existsById(id)) {
            throw new ResourceNotFoundException("Orden no encontrada");
        }
        orderRepository.deleteById(id);
    }
}
