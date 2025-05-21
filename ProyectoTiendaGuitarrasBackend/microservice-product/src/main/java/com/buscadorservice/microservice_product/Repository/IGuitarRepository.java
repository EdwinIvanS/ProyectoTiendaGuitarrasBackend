package com.buscadorservice.microservice_product.Repository;

import com.buscadorservice.microservice_product.Infraestructure.model.Guitar;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IGuitarRepository extends JpaRepository<Guitar, Long>{
    Optional<Guitar> findByName(String name);
}
