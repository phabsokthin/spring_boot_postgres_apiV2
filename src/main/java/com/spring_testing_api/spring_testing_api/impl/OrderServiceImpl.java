package com.spring_testing_api.spring_testing_api.impl;

import org.springframework.stereotype.Service;

import com.spring_testing_api.spring_testing_api.rabbitmq.request.OrderRequest;
import com.spring_testing_api.spring_testing_api.rabbitmq.producer.OrderProducer;
import com.spring_testing_api.spring_testing_api.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderProducer orderProducer;

    public OrderServiceImpl(
            OrderProducer orderProducer
    ) {
        this.orderProducer = orderProducer;
    }

    @Override
    public void createOrder(
            OrderRequest request
    ) {

        // Normally:
        // 1. Validate request
        // 2. Save order to PostgreSQL
        // 3. Get generated order ID

        Long orderId = 1002L;

        System.out.println(
                "Order created: " + orderId
        );

        
        // Send background job
        orderProducer.sendOrderCreated(
                orderId
        );
    }
}