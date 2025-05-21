package com.buscadorservice.microservice_product.Controller;

import com.buscadorservice.microservice_product.Infraestructure.dto.*;
import com.buscadorservice.microservice_product.Service.IGuitarService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/guitarras")
@Validated
public class GuitarController {
    private final IGuitarService service;

    public GuitarController(IGuitarService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<GuitarResponseDto> guitars = service.getAll();
        if (guitars.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(new ResponseGeneric<>("Productos encontrados", guitars));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable @Positive(message = "El ID debe ser mayor que cero") Long id) {
        GuitarResponseDto guitar = service.getById(id);
        return ResponseEntity.ok(new ResponseGeneric<>("Producto encontrado", guitar));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) {
        GuitarResponseDto guitar = service.findByName(name);
        return ResponseEntity.ok(new ResponseGeneric<>("Producto encontrado", guitar));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid GuitarRequestDto dto) {
        GuitarResponseDto created = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseGeneric<>("Producto creado", created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable @Positive(message = "El ID debe ser mayor que cero") Long id,
            @RequestBody @Valid GuitarRequestDto dto) {
        GuitarResponseDto updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable @Positive(message = "El ID debe ser mayor que cero") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
