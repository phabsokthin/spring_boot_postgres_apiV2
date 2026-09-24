package com.spring_testing_api.spring_testing_api.repository;

import com.spring_testing_api.spring_testing_api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsBySku(String sku);
}