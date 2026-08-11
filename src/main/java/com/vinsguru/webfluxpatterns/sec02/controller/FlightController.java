package com.vinsguru.webfluxpatterns.sec02.controller;

import com.vinsguru.webfluxpatterns.sec02.dto.FlightResult;
import com.vinsguru.webfluxpatterns.sec02.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @GetMapping(value = "/flights", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<FlightResult> streamFlights(String from, String to) {
        return flightService.getFlights(from, to);
    }
}
