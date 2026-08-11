package com.vinsguru.webfluxpatterns.sec01.client;

import com.vinsguru.webfluxpatterns.sec01.dto.Promotion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class PromotionClient {

    private final WebClient webClient;

    public PromotionClient(@Value("${sec01.promotion.service}") String baseUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public Mono<Promotion> getPromotionById(Integer id) {
        return this.webClient.get()
                .uri("{id}", id)
                .retrieve()
                .bodyToMono(Promotion.class);
    }
 }
