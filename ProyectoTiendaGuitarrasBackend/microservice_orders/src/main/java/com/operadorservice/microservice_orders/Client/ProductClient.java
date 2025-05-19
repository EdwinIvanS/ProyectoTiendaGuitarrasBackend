package com.operadorservice.microservice_orders.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.operadorservice.microservice_orders.Infraestructure.dto.ProductResponse;

@FeignClient(name = "MICROSERVICE-PRODUCT")
public interface ProductClient {
    @GetMapping("/api/guitarras/{id}")
    ProductResponse getProductById(@PathVariable("id") Long id);
}
