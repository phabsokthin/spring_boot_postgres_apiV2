package com.spring_testing_api.spring_testing_api.rabbitmq.producer;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.spring_testing_api.spring_testing_api.config.RabbitMQConfig;
import com.spring_testing_api.spring_testing_api.rabbitmq.request.OrderCreatedMessage;

@Service
public class OrderProducer {

    private final RabbitTemplate rabbitTemplate;

    public OrderProducer(
            RabbitTemplate rabbitTemplate
    ) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendOrderCreated(Long orderId) {

        OrderCreatedMessage message =
                new OrderCreatedMessage(orderId);

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.ORDER_EXCHANGE,
                RabbitMQConfig.ORDER_CREATED,
                message
        );

        System.out.println(
                ">> Order background job sent: "
                        + orderId
        );
    }
}