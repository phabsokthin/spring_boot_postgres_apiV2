package com.spring_testing_api.spring_testing_api.validation;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class ProductRequest {

    @NotBlank(message = "Product name is required")
    @Size(
            min = 2,
            max = 255,
            message = "Product name must be between 2 and 255 characters"
    )
    private String productName;

    @Size(
            max = 100,
            message = "SKU must not exceed 100 characters"
    )
    private String sku;

    @Size(
            max = 500,
            message = "Description must not exceed 500 characters"
    )
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(
            value = "0.00",
            inclusive = true,
            message = "Price must be greater than or equal to 0"
    )
    private BigDecimal price;

    @NotNull(message = "Quantity is required")
    @Min(
            value = 0,
            message = "Quantity must be greater than or equal to 0"
    )
    private Integer quantity;

    private Boolean status = true;

    @NotNull(message = "Category is required")
    private Long categoryId;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}