package com.spring_testing_api.spring_testing_api.service;

import com.spring_testing_api.spring_testing_api.rabbitmq.request.OrderRequest;

public interface OrderService {

    void createOrder(OrderRequest request);
}