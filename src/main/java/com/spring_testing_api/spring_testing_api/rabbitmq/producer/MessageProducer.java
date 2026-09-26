package com.spring_testing_api.spring_testing_api.rabbitmq.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.spring_testing_api.spring_testing_api.config.RabbitMQConfig;

@Service
public class MessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public MessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message) {

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.QUEUE_NAME,
                message
        );

        System.out.println(
                ">> Message sent: " + message
        );
    }
}