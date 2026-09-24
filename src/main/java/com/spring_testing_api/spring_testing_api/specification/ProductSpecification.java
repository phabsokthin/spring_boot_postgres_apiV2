package com.spring_testing_api.spring_testing_api.specification;

import com.spring_testing_api.spring_testing_api.entity.Product;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {

    private ProductSpecification() {
    }

    public static Specification<Product> search(String keyword) {
        return (root, query, criteriaBuilder) -> {
            if (keyword == null || keyword.trim().isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            String searchPattern = "%" + keyword.trim().toLowerCase() + "%";

            List<Predicate> predicates = new ArrayList<>();

            // 1. Search by Product Name
            if (root.get("productName") != null) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("productName")),
                        searchPattern
                ));
            }

            // 2. Search by SKU
            if (root.get("sku") != null) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("sku")),
                        searchPattern
                ));
            }

            // 3. Search by Description
            if (root.get("description") != null) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("description")),
                        searchPattern
                ));
            }

            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };
    }
}