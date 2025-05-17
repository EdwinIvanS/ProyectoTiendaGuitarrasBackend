package com.buscadorservice.microservice_product.Controller;

import com.buscadorservice.microservice_product.Infraestructure.dto.*;
import com.buscadorservice.microservice_product.Infraestructure.exception.ResourceNotFoundException;
import com.buscadorservice.microservice_product.Service.IGuitarService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guitarras")
public class GuitarController {
    private final IGuitarService service;

    public GuitarController(IGuitarService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            List<GuitarResponseDto> guitars = service.getAll();
            if (guitars.isEmpty())
                return ResponseEntity.noContent().build();
            return ResponseEntity.ok(guitars);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            GuitarResponseDto guitar = service.getById(id);
            return ResponseEntity.ok(guitar);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid GuitarRequestDto dto) {
        try {
            GuitarResponseDto created = service.create(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear la guitarra: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid GuitarRequestDto dto) {
        try {
            GuitarResponseDto updated = service.update(id, dto);
            return ResponseEntity.ok(updated);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar la guitarra: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar la guitarra: " + e.getMessage());
        }
    }
}
