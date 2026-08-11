package com.vinsguru.webfluxpatterns.sec02.client;

import com.vinsguru.webfluxpatterns.sec02.dto.FlightResult;
import com.vinsguru.webfluxpatterns.sec02.dto.FrontierRequestBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class FrontierClient {

    private final WebClient webClient;

    public FrontierClient(@Value("${sec02.frontier.service}") String baseUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public Flux<FlightResult> getFrontierFlights(String from, String to) {
        FrontierRequestBody requestBody = new FrontierRequestBody(from, to);
        return this.webClient.post()
                .bodyValue(requestBody)
                .retrieve()
                .bodyToFlux(FlightResult.class)
                .onErrorResume(ex -> Mono.empty());

    }
}
