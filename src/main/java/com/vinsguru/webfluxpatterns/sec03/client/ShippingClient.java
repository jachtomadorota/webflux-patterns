package com.vinsguru.webfluxpatterns.sec03.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ShippingClient {

    private final WebClient webClient;

    public ShippingClient(@Value("${sec03.shipping.service}") String baseUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }
}
