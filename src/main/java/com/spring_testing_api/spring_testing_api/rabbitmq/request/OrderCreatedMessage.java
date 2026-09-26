package com.spring_testing_api.spring_testing_api.rabbitmq.request;

import java.io.Serializable;

public class OrderCreatedMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long orderId;

    public OrderCreatedMessage() {
    }

    public OrderCreatedMessage(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
