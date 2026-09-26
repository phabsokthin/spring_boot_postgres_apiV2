package com.spring_testing_api.spring_testing_api.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spring_testing_api.spring_testing_api.dto.ApiResponse;
import com.spring_testing_api.spring_testing_api.rabbitmq.request.OrderRequest;
import com.spring_testing_api.spring_testing_api.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(
            OrderService orderService
    ) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<String>> createOrder(
            @RequestBody OrderRequest request
    ) {

        orderService.createOrder(request);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Order created successfully",
                        null
                )
        );
    }
}