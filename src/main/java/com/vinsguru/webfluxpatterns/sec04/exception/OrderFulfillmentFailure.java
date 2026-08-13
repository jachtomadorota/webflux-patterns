package com.vinsguru.webfluxpatterns.sec04.exception;

public class OrderFulfillmentFailure extends RuntimeException {
    public OrderFulfillmentFailure(String message) {
        super(message);
    }
}
