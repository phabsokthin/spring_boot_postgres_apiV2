package com.spring_testing_api.spring_testing_api.impl;

import com.spring_testing_api.spring_testing_api.entity.Category;
import com.spring_testing_api.spring_testing_api.repository.CategoryRepository;
import com.spring_testing_api.spring_testing_api.service.CategoryService;
import com.spring_testing_api.spring_testing_api.validation.CategoryRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.time.Duration;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

        private static final Duration CACHE_TTL = Duration.ofMinutes(10);
        private final RedisTemplate<String, String> redisTemplate;
        private final CategoryRepository categoryRepository;
        private final SimpMessagingTemplate messagingTemplate;

        public CategoryServiceImpl(CategoryRepository categoryRepository, RedisTemplate<String, String> redisTemplate,
                        SimpMessagingTemplate messagingTemplate) {
                this.categoryRepository = categoryRepository;
                this.redisTemplate = redisTemplate;
                this.messagingTemplate = messagingTemplate;
        }

        @Override
        public List<Category> getAll() {
                // return categoryRepository.findAllByOrderByCategoryIdDesc();

                // store in redis
                  List<Category> categories = categoryRepository.findAllByOrderByCategoryIdDesc();

                  // Store in Redis with TTL
                  redisTemplate.opsForValue().set(
                                  "categories_list",
                                  categories.toString(),
                                  CACHE_TTL);

                  return categories;
        }

        // @Override
        // public List<Category> getAllWithProducts() {
        // return categoryRepository.findAllWithProducts();
        // }

        @Override
        public List<Category> getAllWithProducts() {

                List<Category> categories = categoryRepository.findAllWithProducts();

                // Store in Redis with TTL
                redisTemplate.opsForValue().set(
                                "categories",
                                categories.toString(),
                                CACHE_TTL);

                return categories;
        }

        @Override
        public Category getById(Long id) {
                return categoryRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException(
                                                "Category not found with id: " + id));
        }

        // @Override
        // public Category create(CategoryRequest request) {

        // if (categoryRepository.existsByCategoryName(
        // request.getCategoryName())) {
        // throw new RuntimeException(
        // "Category name already exists");
        // }

        // Category category = new Category();

        // category.setCategoryName(request.getCategoryName());
        // category.setDescription(request.getDescription());
        // category.setStatus(request.getStatus());

        // return categoryRepository.save(category);
        // }

        @Override
        public Category create(CategoryRequest request) {

                Category category = new Category();

                category.setCategoryName(request.getCategoryName());
                category.setDescription(request.getDescription());
                category.setStatus(request.getStatus());

                // Save to PostgreSQL
                Category savedCategory = categoryRepository.save(category);

                // Store in Redis with TTL
                storeCategoryInRedis(savedCategory.getCategoryId(), savedCategory.getCategoryName());

                // Send CREATE notification via WebSocket
                try {
                        messagingTemplate.convertAndSend(
                                        "/topic/categories",
                                        (Object) Map.of(
                                                        "action", "CREATE",
                                                        "category", savedCategory));
                } catch (Exception e) {
                        System.err.println(">> [WebSocket Error] Failed to send CREATE message: " + e.getMessage());
                        e.printStackTrace();
                }

                return savedCategory;
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

                Category updatedCategory = categoryRepository.save(category);

                // Update Redis cache with TTL
                storeCategoryInRedis(updatedCategory.getCategoryId(), updatedCategory.getCategoryName());

                // Send UPDATE notification via WebSocket
                try {
                        messagingTemplate.convertAndSend(
                                        "/topic/categories",
                                        (Object) Map.of(
                                                        "action", "UPDATE",
                                                        "category", updatedCategory));
                } catch (Exception e) {
                        System.err.println(">> [WebSocket Error] Failed to send UPDATE message: " + e.getMessage());
                        e.printStackTrace();
                }

                return updatedCategory;
        }

        @Override
        public void delete(Long id) {

                Category category = categoryRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException(
                                                "Category not found with id: " + id));

                categoryRepository.delete(category);

                // Evict from Redis
                redisTemplate.delete("category:" + id);

                // Send DELETE notification via WebSocket
                try {
                        messagingTemplate.convertAndSend(
                                        "/topic/categories",
                                        (Object) Map.of(
                                                        "action", "DELETE",
                                                        "categoryId", id));
                } catch (Exception e) {
                        System.err.println(">> [WebSocket Error] Failed to send DELETE message: " + e.getMessage());
                        e.printStackTrace();
                }

        }

        // STORE CATEGORY IN REDIS WITH TTL
        @Override
        public void storeCategoryInRedis(
                        Long id,
                        String name) {

                String key = "category:" + id;

                redisTemplate.opsForValue().set(
                                key,
                                name,
                                CACHE_TTL);
        }
}