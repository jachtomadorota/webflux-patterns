package com.vinsguru.webfluxpatterns.sec05.client;

import com.vinsguru.webfluxpatterns.sec05.dto.car.CarReservationRequest;
import com.vinsguru.webfluxpatterns.sec05.dto.car.CarReservationResponse;
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

    public Flux<CarReservationResponse> reserve(Flux<CarReservationRequest> request) {
        return this.webClient.post()
                .uri("reserve")
                .bodyValue(request)
                .retrieve()
                .bodyToFlux(CarReservationResponse.class)
                .onErrorResume(ex -> Mono.empty());
    }
}