package com.spring_testing_api.spring_testing_api.service;

import com.spring_testing_api.spring_testing_api.entity.Product;
import com.spring_testing_api.spring_testing_api.validation.ProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;



public interface ProductService {

    List<Product> getAll();

    Page<Product> getAll(Pageable pageable);

    // search 
    Page<Product> search(
            String search,
            Pageable pageable
    );

    Product getById(Long id);

    Product create(ProductRequest request);

    Product update(Long id, ProductRequest request);

    void delete(Long id);
}