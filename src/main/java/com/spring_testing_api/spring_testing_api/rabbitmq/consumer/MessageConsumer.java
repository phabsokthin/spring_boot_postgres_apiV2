package com.spring_testing_api.spring_testing_api.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.spring_testing_api.spring_testing_api.config.RabbitMQConfig;

@Service
public class MessageConsumer {

    @RabbitListener(
            queues = RabbitMQConfig.QUEUE_NAME
    )
    public void receiveMessage(String message) {

        System.out.println(
                ">> Message received: " + message
        );
    }
}