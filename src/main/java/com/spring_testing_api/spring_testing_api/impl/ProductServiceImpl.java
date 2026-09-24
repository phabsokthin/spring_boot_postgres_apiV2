package com.spring_testing_api.spring_testing_api.impl;

import com.spring_testing_api.spring_testing_api.entity.Category;
import com.spring_testing_api.spring_testing_api.entity.Product;
import com.spring_testing_api.spring_testing_api.repository.CategoryRepository;
import com.spring_testing_api.spring_testing_api.repository.ProductRepository;
import com.spring_testing_api.spring_testing_api.service.ProductService;
import com.spring_testing_api.spring_testing_api.validation.ProductRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    // Inject repository 
    public ProductServiceImpl(
            ProductRepository productRepository,
            CategoryRepository categoryRepository
    ) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(Long id) {

        return productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Product not found with id: " + id
                        )
                );
    }

    @Override
    public Product create(ProductRequest request) {

        // Check duplicate SKU
        if (request.getSku() != null
                && !request.getSku().isBlank()
                && productRepository.existsBySku(request.getSku())) {

            throw new RuntimeException(
                    "SKU already exists"
            );
        }

        // Find category
        Category category = categoryRepository.findById(
                request.getCategoryId()
        ).orElseThrow(() ->
                new RuntimeException(
                        "Category not found with id: "
                                + request.getCategoryId()
                )
        );

        Product product = new Product();

        product.setProductName(request.getProductName());
        product.setSku(request.getSku());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setStatus(request.getStatus());
        product.setCategory(category);

        return productRepository.save(product);
    }

    @Override
    public Product update(
            Long id,
            ProductRequest request
    ) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Product not found with id: " + id
                        )
                );

        // Check duplicate SKU
        if (request.getSku() != null
                && !request.getSku().isBlank()
                && !request.getSku().equals(product.getSku())
                && productRepository.existsBySku(request.getSku())) {

            throw new RuntimeException(
                    "SKU already exists"
            );
        }

        // Find category
        Category category = categoryRepository.findById(
                request.getCategoryId()
        ).orElseThrow(() ->
                new RuntimeException(
                        "Category not found with id: "
                                + request.getCategoryId()
                )
        );

        product.setProductName(request.getProductName());
        product.setSku(request.getSku());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setStatus(request.getStatus());
        product.setCategory(category);

        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Product not found with id: " + id
                        )
                );

        productRepository.delete(product);
    }
}