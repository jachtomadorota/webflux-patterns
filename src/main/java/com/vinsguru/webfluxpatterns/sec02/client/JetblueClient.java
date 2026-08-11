package com.vinsguru.webfluxpatterns.sec02.client;

import com.vinsguru.webfluxpatterns.sec02.dto.FlightResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class JetblueClient {

    private static final String JETBLUE_AIRLINE = "JETBLUE";
    private final WebClient webClient;

    public JetblueClient(@Value("${sec02.jetblue.service}") String baseUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public Flux<FlightResult> getJetblueFlights(String from, String to) {
        return this.webClient.get()
                .uri("{from}/{to}", from, to)
                .retrieve()
                .bodyToFlux(FlightResult.class)
                .doOnNext(fr -> this.normalizeResponse(fr, from, to))
                .onErrorResume(ex -> Mono.empty());
    }

    private void normalizeResponse(FlightResult response, String from, String to) {
        response.withToAndFromAndAirline(to, from, JETBLUE_AIRLINE);
    }
}
