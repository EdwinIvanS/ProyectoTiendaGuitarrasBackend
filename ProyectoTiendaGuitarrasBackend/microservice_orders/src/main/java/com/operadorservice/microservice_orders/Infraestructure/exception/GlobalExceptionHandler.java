package com.operadorservice.microservice_orders.Infraestructure.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.operadorservice.microservice_orders.Infraestructure.dto.ResponseGeneric;

import feign.FeignException;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ResponseGeneric<String>> handleNotFound(ResourceNotFoundException ex) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(new ResponseGeneric<>("Recurso no encontrado", ex.getMessage()));
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {
                Map<String, String> errors = new HashMap<>();

                ex.getBindingResult().getFieldErrors()
                                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

                return ResponseEntity.badRequest().body(
                                new ResponseGeneric<>("Error de validación", errors));
        }

        @ExceptionHandler(ConstraintViolationException.class)
        public ResponseEntity<ResponseGeneric<String>> handleConstraintViolation(ConstraintViolationException ex) {
                String errorMsg = ex.getConstraintViolations().stream()
                                .map(v -> v.getMessage())
                                .findFirst()
                                .orElse("Parámetro inválido");

                return ResponseEntity.badRequest()
                                .body(new ResponseGeneric<>("Error de validación", errorMsg));
        }

        @ExceptionHandler(FeignException.NotFound.class)
        public ResponseEntity<ResponseGeneric<String>> handleFeignNotFound(FeignException.NotFound ex) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(new ResponseGeneric<>("No se puede crear el pedido, producto no existe", null));
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ResponseGeneric<String>> handleGeneric(Exception ex) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(new ResponseGeneric<>("Error interno", ex.getMessage()));
        }

        @ExceptionHandler(MethodArgumentTypeMismatchException.class)
        public ResponseEntity<ResponseGeneric<String>> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
                String fieldName = ex.getName();
                String errorMessage = String.format("El parámetro '%s' debe ser un número válido", fieldName);

                return ResponseEntity
                                .status(HttpStatus.BAD_REQUEST)
                                .body(new ResponseGeneric<>("Error de validación", errorMessage));
        }
}
