package com.spring_testing_api.spring_testing_api.impl;

import com.spring_testing_api.spring_testing_api.entity.Category;
import com.spring_testing_api.spring_testing_api.repository.CategoryRepository;
import com.spring_testing_api.spring_testing_api.service.CategoryService;
import com.spring_testing_api.spring_testing_api.validation.CategoryRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

        private final CategoryRepository categoryRepository;

        public CategoryServiceImpl(CategoryRepository categoryRepository) {
                this.categoryRepository = categoryRepository;
        }

        @Override
        public List<Category> getAll() {
                return categoryRepository.findAll();
        }

        @Override
        public List<Category> getAllWithProducts() {
                return categoryRepository.findAllWithProducts();
        }

        @Override
        public Category getById(Long id) {
                return categoryRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException(
                                                "Category not found with id: " + id));
        }

        @Override
        public Category create(CategoryRequest request) {

                if (categoryRepository.existsByCategoryName(
                                request.getCategoryName())) {
                        throw new RuntimeException(
                                        "Category name already exists");
                }

                Category category = new Category();

                category.setCategoryName(request.getCategoryName());
                category.setDescription(request.getDescription());
                category.setStatus(request.getStatus());

                return categoryRepository.save(category);
        }

        @Override
        public Category update(
                        Long id,
                        CategoryRequest request) {

                Category category = categoryRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException(
                                                "Category not found with id: " + id));

                // Check duplicate name
                if (!category.getCategoryName()
                                .equals(request.getCategoryName())
                                && categoryRepository.existsByCategoryName(
                                                request.getCategoryName())) {

                        throw new RuntimeException(
                                        "Category name already exists");
                }

                category.setCategoryName(request.getCategoryName());
                category.setDescription(request.getDescription());
                category.setStatus(request.getStatus());

                return categoryRepository.save(category);
        }

        @Override
        public void delete(Long id) {

                Category category = categoryRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException(
                                                "Category not found with id: " + id));

                categoryRepository.delete(category);
        }
}