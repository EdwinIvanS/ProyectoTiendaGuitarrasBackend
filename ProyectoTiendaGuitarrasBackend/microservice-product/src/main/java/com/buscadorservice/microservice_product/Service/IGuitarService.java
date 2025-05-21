package com.buscadorservice.microservice_product.Service;
import com.buscadorservice.microservice_product.Infraestructure.dto.*;
import java.util.List;

public interface IGuitarService {
    List<GuitarResponseDto> getAll();
    GuitarResponseDto getById(Long id);
    GuitarResponseDto findByName(String name);
    GuitarResponseDto create(GuitarRequestDto dto);
    GuitarResponseDto update(Long id, GuitarRequestDto dto);
    void delete(Long id);
}
