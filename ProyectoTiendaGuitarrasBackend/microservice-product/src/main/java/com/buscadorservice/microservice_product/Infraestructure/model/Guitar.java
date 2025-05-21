package com.buscadorservice.microservice_product.Infraestructure.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Data
public class Guitar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;
    private String description;
    private double price;
    private Integer stock;
}
