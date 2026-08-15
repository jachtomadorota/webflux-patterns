package com.vinsguru.webfluxpatterns.sec05.client;

import com.vinsguru.webfluxpatterns.sec04.dto.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
public class RoomClient {

    private final WebClient webClient;

    public RoomClient(@Value("${sec05.room.service}") String baseUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public Flux<Product> reserve() {
        return this.webClient.get()
                .uri("reserve")
                .retrieve()
                .bodyToFlux(Product.class);
    }
}