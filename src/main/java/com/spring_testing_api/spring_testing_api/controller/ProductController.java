package com.spring_testing_api.spring_testing_api.controller;

import com.spring_testing_api.spring_testing_api.dto.ApiResponse;
import com.spring_testing_api.spring_testing_api.entity.Product;
import com.spring_testing_api.spring_testing_api.service.ProductService;
import com.spring_testing_api.spring_testing_api.validation.ProductRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(
            ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Product>>> getAll() {

        List<Product> products = productService.getAll();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Products retrieved successfully",
                        products));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> getById(
            @PathVariable Long id) {

        Product product = productService.getById(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Product retrieved successfully",
                        product));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Product>> create(
            @Valid @RequestBody ProductRequest request) {

        Product product = productService.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        new ApiResponse<>(
                                true,
                                "Product created successfully",
                                product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request) {

        Product product = productService.update(id, request);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Product updated successfully",
                        product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long id) {

        productService.delete(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Product deleted successfully",
                        null));
    }
}