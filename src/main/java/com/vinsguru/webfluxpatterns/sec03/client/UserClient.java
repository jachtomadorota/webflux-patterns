package com.vinsguru.webfluxpatterns.sec03.client;

import com.vinsguru.webfluxpatterns.sec03.dto.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class UserClient {

    private final WebClient webClient;

    public UserClient(@Value("${sec03.client.service}") String baseUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public Mono<User> getUserById(Integer id) {
        return this.webClient.get()
                .uri("{id}", id)
                .retrieve()
                .bodyToMono(User.class)
                .onErrorResume(ex -> Mono.empty());
    }
}
