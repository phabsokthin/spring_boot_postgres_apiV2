package com.spring_testing_api.spring_testing_api.service;

import com.spring_testing_api.spring_testing_api.validation.CategoryRequest;
import com.spring_testing_api.spring_testing_api.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAll();

    List<Category> getAllWithProducts();

    Category getById(Long id);

    Category create(CategoryRequest request);

    Category update(Long id, CategoryRequest request);

    void delete(Long id);

    // redis
    void storeCategoryInRedis(Long id, String name);
    
}