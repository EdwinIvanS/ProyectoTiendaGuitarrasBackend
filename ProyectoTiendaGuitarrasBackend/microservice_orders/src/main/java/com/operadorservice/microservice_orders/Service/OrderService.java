package com.operadorservice.microservice_orders.Service;

import com.operadorservice.microservice_orders.Infraestructure.dto.*;
import com.operadorservice.microservice_orders.Infraestructure.model.Order;
import com.operadorservice.microservice_orders.Repository.IOrderRepository;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.stereotype.Service;
import com.operadorservice.microservice_orders.Infraestructure.exception.ResourceNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import reactor.core.publisher.Mono;

@Service
public class OrderService implements IOrderService{
    private IOrderRepository orderRepository;
    private final WebClient webClient;
    
    public OrderService(IOrderRepository orderRepository, WebClient.Builder webClientBuilder) {
        this.orderRepository = orderRepository;
        this.webClient = webClientBuilder.baseUrl("http://localhost:8081").build();
    }
    
    @Override
    public Order createOrder(OrderRequest request) {
        ProductResponse product = webClient.get()
                .uri("/api/guitarras/{id}", request.getProductId())
                .retrieve()
                .onStatus(
                        status -> !status.is2xxSuccessful(),
                        clientResponse -> Mono.error(new ResourceNotFoundException("Producto no encontrado"))
                )
                .bodyToMono(ProductResponse.class)
                .block();

        double total = BigDecimal.valueOf(product.getPrice()).multiply(BigDecimal.valueOf(request.getQuantity())).doubleValue();

        Order order = Order.builder()
                .productId(product.getId())
                .productName(product.getName())
                .price(product.getPrice())
                .quantity(request.getQuantity())
                .total(total)
                .imageUrl(product.getImage())
                .createdAt(LocalDateTime.now())
                .build();

        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }    
}
