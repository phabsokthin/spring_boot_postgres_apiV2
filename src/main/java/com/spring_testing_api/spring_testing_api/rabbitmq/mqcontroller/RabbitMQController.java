
package com.spring_testing_api.spring_testing_api.rabbitmq.mqcontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spring_testing_api.spring_testing_api.dto.ApiResponse;
import com.spring_testing_api.spring_testing_api.rabbitmq.producer.MessageProducer;
import com.spring_testing_api.spring_testing_api.rabbitmq.request.RabbitMessageRequest;

@RestController
@RequestMapping("/api/rabbitmq")
public class RabbitMQController {

    private final MessageProducer messageProducer;

    public RabbitMQController(
            MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @PostMapping("/send")
    public ResponseEntity<ApiResponse<String>> sendMessage(
            @RequestBody RabbitMessageRequest request) {

        messageProducer.sendMessage(
                request.getMessage());

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Message sent successfully",
                        request.getMessage()));
    }

    
}