package com.spring_testing_api.spring_testing_api.controller;

import com.spring_testing_api.spring_testing_api.dto.ApiResponse;
import com.spring_testing_api.spring_testing_api.entity.Category;
import com.spring_testing_api.spring_testing_api.service.CategoryService;
import com.spring_testing_api.spring_testing_api.validation.CategoryRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

        private final CategoryService categoryService;

        public CategoryController(CategoryService categoryService) {
                this.categoryService = categoryService;
        }

        // GET ALL
        @GetMapping
        public ResponseEntity<ApiResponse<List<Category>>> getAll() {

                List<Category> categories = categoryService.getAll();

                return ResponseEntity.ok(
                                new ApiResponse<>(
                                                true,
                                                "Categories retrieved successfully",
                                                categories));
        }



        // GET ALL
        @GetMapping("/getAllWithProducts")
        public ResponseEntity<ApiResponse<List<Category>>> getAllWithProducts() {

                List<Category> categories = categoryService.getAllWithProducts();

                return ResponseEntity.ok(
                                new ApiResponse<>(
                                                true,
                                                "Categories retrieved successfully",
                                                categories));
        }

        // GET BY ID
        @GetMapping("/{id}")
        public ResponseEntity<ApiResponse<Category>> getById(
                        @PathVariable Long id) {

                Category category = categoryService.getById(id);

                return ResponseEntity.ok(
                                new ApiResponse<>(
                                                true,
                                                "Category retrieved successfully",
                                                category));
        }

        // CREATE
        @PostMapping
        public ResponseEntity<ApiResponse<Category>> create(
                        @Valid @RequestBody CategoryRequest request) {

                Category category = categoryService.create(request);

                return ResponseEntity
                                .status(HttpStatus.CREATED)
                                .body(
                                                new ApiResponse<>(
                                                                true,
                                                                "Category created successfully",
                                                                category));
        }

        // UPDATE
        @PutMapping("/{id}")
        public ResponseEntity<ApiResponse<Category>> update(
                        @PathVariable Long id,
                        @Valid @RequestBody CategoryRequest request) {

                Category category = categoryService.update(id, request);

                return ResponseEntity.ok(
                                new ApiResponse<>(
                                                true,
                                                "Category updated successfully",
                                                category));
        }

        // DELETE
        @DeleteMapping("/{id}")
        public ResponseEntity<ApiResponse<Void>> delete(
                        @PathVariable Long id) {

                categoryService.delete(id);

                return ResponseEntity.ok(
                                new ApiResponse<>(
                                                true,
                                                "Category deleted successfully",
                                                null));
        }
}