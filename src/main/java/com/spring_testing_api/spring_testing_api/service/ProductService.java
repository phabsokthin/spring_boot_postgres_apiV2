package com.spring_testing_api.spring_testing_api.service;

import com.spring_testing_api.spring_testing_api.entity.Product;
import com.spring_testing_api.spring_testing_api.validation.ProductRequest;

import java.util.List;

public interface ProductService {

    List<Product> getAll();

    Product getById(Long id);

    Product create(ProductRequest request);

    Product update(Long id, ProductRequest request);

    void delete(Long id);
}