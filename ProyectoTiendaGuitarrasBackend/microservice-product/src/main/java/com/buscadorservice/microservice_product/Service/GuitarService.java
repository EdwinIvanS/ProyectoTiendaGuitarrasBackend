package com.buscadorservice.microservice_product.Service;

import com.buscadorservice.microservice_product.Infraestructure.dto.GuitarRequestDto;
import com.buscadorservice.microservice_product.Infraestructure.dto.GuitarResponseDto;
import com.buscadorservice.microservice_product.Infraestructure.exception.ResourceNotFoundException;
import com.buscadorservice.microservice_product.Repository.IGuitarRepository;
import com.buscadorservice.microservice_product.Infraestructure.model.Guitar;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuitarService implements IGuitarService {

    private final IGuitarRepository repository;

    public GuitarService(IGuitarRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<GuitarResponseDto> getAll() {
        List<GuitarResponseDto> response = repository.findAll().stream()
                .map(g -> new GuitarResponseDto(g.getId(), g.getName(), g.getImage(), g.getDescription(), g.getPrice(),
                        g.getStock()))
                .toList();
        return response;
    }

    @Override
    public GuitarResponseDto getById(Long id) {
        Guitar g = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Guitarra no encontrada"));
        return new GuitarResponseDto(g.getId(), g.getName(), g.getImage(), g.getDescription(), g.getPrice(),
                g.getStock());
    }

    @Override
    public GuitarResponseDto findByName(String name){
        Guitar g = repository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Guitarra no encontrada"));
        return new GuitarResponseDto(g.getId(), g.getName(), g.getImage(), g.getDescription(), g.getPrice(),
                g.getStock());
    }

    @Override
    public GuitarResponseDto create(GuitarRequestDto dto) {
        Guitar g = new Guitar();
        g.setName(dto.getName());
        g.setImage(dto.getImage());
        g.setDescription(dto.getDescription());
        g.setPrice(dto.getPrice());
        g.setStock(dto.getStock());
        repository.save(g);
        return new GuitarResponseDto(g.getId(), g.getName(), g.getImage(), g.getDescription(), g.getPrice(),
                g.getStock());
    }

    @Override
    public GuitarResponseDto update(Long id, GuitarRequestDto dto) {
        Guitar g = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Guitarra no encontrada"));
        g.setName(dto.getName());
        g.setImage(dto.getImage());
        g.setDescription(dto.getDescription());
        g.setPrice(dto.getPrice());
        g.setStock(dto.getStock());
        repository.save(g);
        return new GuitarResponseDto(g.getId(), g.getName(), g.getImage(), g.getDescription(), g.getPrice(),
                g.getStock());
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Guitarra no encontrada");
        }
        repository.deleteById(id);
    }
}