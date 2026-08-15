package com.vinsguru.webfluxpatterns.sec05.client;

import com.vinsguru.webfluxpatterns.sec04.dto.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CarClient {

    private final WebClient webClient;

    public CarClient(@Value("${sec05.car.service}") String baseUrl) {
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