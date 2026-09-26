package com.spring_testing_api.spring_testing_api.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.spring_testing_api.spring_testing_api.config.RabbitMQConfig;
import com.spring_testing_api.spring_testing_api.rabbitmq.request.OrderCreatedMessage;

@Service
public class OrderConsumer {

        @RabbitListener(queues = RabbitMQConfig.ORDER_QUEUE)
        public void consumeOrderCreated(
                        OrderCreatedMessage message) {

                System.out.println(
                                "================================");

                System.out.println(
                                "Background job started");

                System.out.println(
                                "Order ID: "
                                                + message.getOrderId());

                // Background work here

                sendEmail(message.getOrderId());

                System.out.println(
                                "Background job finished");

                System.out.println(
                                "================================");
        }

        private void sendEmail(Long orderId) {

                System.out.println(
                                "Sending email for order: "
                                                + orderId);

                // Email logic
        }
}