package com.vinsguru.webfluxpatterns.sec02.service;

import com.vinsguru.webfluxpatterns.sec02.client.DeltaClient;
import com.vinsguru.webfluxpatterns.sec02.client.FrontierClient;
import com.vinsguru.webfluxpatterns.sec02.client.JetblueClient;
import com.vinsguru.webfluxpatterns.sec02.dto.FlightResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final DeltaClient deltaClient;
    private final FrontierClient frontierClient;
    private JetblueClient jetblueClient;


    public Flux<FlightResult> getFlights(String from, String to) {
        return Flux.merge(deltaClient.getDeltaFlights(from, to),
                jetblueClient.getJetblueFlights(from, to),
                frontierClient.getFrontierFlights(from, to))
                .take(Duration.ofSeconds(1));
    }
}
