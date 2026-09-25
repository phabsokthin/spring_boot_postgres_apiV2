package com.spring_testing_api.spring_testing_api.repository;
import com.spring_testing_api.spring_testing_api.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByCategoryName(String categoryName);

    // find all categories by category_id desc
    List<Category> findAllByOrderByCategoryIdDesc();

     @Query("""
        SELECT DISTINCT c
        FROM Category c
        LEFT JOIN FETCH c.products
    """)
    List<Category> findAllWithProducts();
}