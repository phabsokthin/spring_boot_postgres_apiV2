package com.spring_testing_api.spring_testing_api.rabbitmq.request;

public class RabbitMessageRequest {

    private String message;

    public RabbitMessageRequest() {
    }

    public RabbitMessageRequest(String message) {
        this.message = message;
    }

    // get message
    public String getMessage() {
        return message;
    }

    // set message
    public void setMessage(String message) {
        this.message = message;
    }
}