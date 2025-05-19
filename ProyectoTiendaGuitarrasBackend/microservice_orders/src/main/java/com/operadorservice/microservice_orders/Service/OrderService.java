package com.operadorservice.microservice_orders.Service;

import com.operadorservice.microservice_orders.Client.ProductClient;
import com.operadorservice.microservice_orders.Infraestructure.dto.*;
import com.operadorservice.microservice_orders.Infraestructure.model.Order;
import com.operadorservice.microservice_orders.Repository.IOrderRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService implements IOrderService {
    private IOrderRepository orderRepository;
    private ProductClient productClient;

    public OrderService(IOrderRepository orderRepository, ProductClient productClient) {
        this.orderRepository = orderRepository;
        this.productClient = productClient;
    }

    @Override
    public Order createOrder(OrderRequest request) {

        ProductResponse product = productClient.getProductById(request.getProductId());

        double total = BigDecimal.valueOf(product.getPrice()).multiply(BigDecimal.valueOf(request.getQuantity()))
                .doubleValue();

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
