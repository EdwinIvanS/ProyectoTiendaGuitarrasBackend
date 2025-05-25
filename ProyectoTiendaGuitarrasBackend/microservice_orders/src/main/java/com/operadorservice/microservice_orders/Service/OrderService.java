package com.operadorservice.microservice_orders.Service;

import com.operadorservice.microservice_orders.Client.ProductClient;
import com.operadorservice.microservice_orders.Infraestructure.dto.*;
import com.operadorservice.microservice_orders.Infraestructure.exception.ResourceNotFoundException;
import com.operadorservice.microservice_orders.Infraestructure.mapper.OrderItemMapper;
import com.operadorservice.microservice_orders.Infraestructure.mapper.OrderMapper;
import com.operadorservice.microservice_orders.Infraestructure.model.Order;
import com.operadorservice.microservice_orders.Infraestructure.model.OrderItem;
import com.operadorservice.microservice_orders.Repository.IOrderItemRepository;
import com.operadorservice.microservice_orders.Repository.IOrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {
        private IOrderRepository orderRepository;
        private IOrderItemRepository orderItemRepository;
        private ProductClient productClient;

        public OrderService(IOrderRepository orderRepository, IOrderItemRepository orderItemRepository,
                        ProductClient productClient) {
                this.orderRepository = orderRepository;
                this.orderItemRepository = orderItemRepository;
                this.productClient = productClient;
        }

        @Override
        public List<OrderResponseDto> getAllOrders() {
                List<Order> orders = orderRepository.findAll();
                return orders.stream()
                                .map(OrderMapper::toDto)
                                .collect(Collectors.toList());
        }

        @Override
        public OrderResponseDto findById(String id) {
                Order order = orderRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada con id: " + id));
                return OrderMapper.toDto(order);
        }

        @Override
        public List<OrderResponseDto> findByCustomerName(String customerName) {
                List<Order> orders = orderRepository.findByCustomerNameIgnoreCase(customerName);
                return orders.stream()
                                .map(OrderMapper::toDto)
                                .collect(Collectors.toList());
        }

        @Override
        public OrderResponseDto createOrder(OrderRequestDto request) {
                Order order = Order.builder()
                                .id(UUID.randomUUID().toString())
                                .createdAt(LocalDateTime.now())
                                .customerName(request.getCustomerName())
                                .status("CREATED")
                                .build();

                List<OrderItem> items = request.getItems().stream().map(itemDto -> {
                        ProductResponseWrapper productWrapper = productClient.getProductById(itemDto.getProductId());
                        ProductResponse product = productWrapper.getData();
                        System.out.println("producto: " + product);
                        if (product == null) {
                                throw new ResourceNotFoundException(
                                                "Producto no encontrado: " + itemDto.getProductId());
                        }

                        if (product.getStock() < itemDto.getQuantity()) {
                                throw new ResourceNotFoundException("Producto fuera de stock: " + product.getName());
                        }

                        return OrderItemMapper.fromRequestDto(itemDto, order, product);
                }).collect(Collectors.toList());

                order.setItems(items);

                Order savedOrder = orderRepository.save(order);

                return OrderMapper.toDto(savedOrder);
        }

        @Override
        public OrderResponseDto update(String id, OrderRequestDto dto) {
                Order order = orderRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada con id: " + id));

                order.setCustomerName(dto.getCustomerName());

                order.getItems().clear();

                List<OrderItem> updatedItems = dto.getItems().stream().map(itemDto -> {
                        ProductResponseWrapper productWrapper = productClient.getProductById(itemDto.getProductId());
                        ProductResponse product = productWrapper.getData();
                        if (product == null) {
                                throw new ResourceNotFoundException(
                                                "Producto no encontrado: " + itemDto.getProductId());
                        }

                        if (product.getStock() < itemDto.getQuantity()) {
                                throw new ResourceNotFoundException("Producto fuera de stock: " + product.getName());
                        }

                        return OrderItemMapper.fromRequestDto(itemDto, order, product);
                }).collect(Collectors.toList());

                order.getItems().addAll(updatedItems);

                Order updatedOrder = orderRepository.save(order);

                return OrderMapper.toDto(updatedOrder);
        }

        @Override
        public void delete(String id) {
                Order order = orderRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada con id: " + id));
                orderRepository.delete(order);
        }

}
