package com.vinsguru.webfluxpatterns.sec01.client;

import com.vinsguru.webfluxpatterns.sec01.dto.Review;
import com.vinsguru.webfluxpatterns.sec01.dto.ReviewsWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Component
public class ReviewClient {

    private final WebClient webClient;

    public ReviewClient(@Value("${sec01.review.service}") String baseUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public Mono<List<Review>> getReviewById(Integer id) {
        return this.webClient.get()
                .uri("{id}", id)
                .retrieve()
                .bodyToFlux(Review.class)
                .collectList()
                .onErrorReturn(Collections.emptyList());
    }
}
