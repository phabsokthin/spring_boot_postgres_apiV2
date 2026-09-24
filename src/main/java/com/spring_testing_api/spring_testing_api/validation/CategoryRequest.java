package com.spring_testing_api.spring_testing_api.validation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryRequest {

    @NotBlank(message = "Category name is required")
    @Size(
        min = 2,
        max = 255,
        message = "Category name must be between 2 and 255 characters"
    )
    private String categoryName;
    
    @Size(
        max = 500,
        message = "Description must not exceed 500 characters"
    )
    private String description;

    private Boolean status = true;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}