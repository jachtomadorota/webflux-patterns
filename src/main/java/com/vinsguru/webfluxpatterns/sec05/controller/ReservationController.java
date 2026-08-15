package com.vinsguru.webfluxpatterns.sec05.controller;

import com.vinsguru.webfluxpatterns.sec05.dto.reservation.ReservationItemRequest;
import com.vinsguru.webfluxpatterns.sec05.dto.reservation.ReservationResponse;
import com.vinsguru.webfluxpatterns.sec05.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservations")
public class ReservationController {


    private final ReservationService reservationService;

    @PostMapping
    public Mono<ReservationResponse> reserve(@RequestBody Flux<ReservationItemRequest> items) {
        return reservationService.reserve(items);
    }
}
