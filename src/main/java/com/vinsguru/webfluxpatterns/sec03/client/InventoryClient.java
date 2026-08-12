package com.vinsguru.webfluxpatterns.sec03.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class InventoryClient {

    private final WebClient webClient;

    public InventoryClient(@Value("${sec03.inventory.service}") String baseUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public Mono<Integer> getInventoryById(Integer id) {
        return this.webClient.get()
                .uri("{id}", id)
                .retrieve()
                .bodyToMono(Integer.class)
                .onErrorResume(ex -> Mono.empty());
    }
}
