package com.buscadorservice.microservice_product.Repository;

import com.buscadorservice.microservice_product.Infraestructure.model.Guitar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGuitarRepository extends JpaRepository<Guitar, Long>{}
