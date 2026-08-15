package com.vinsguru.webfluxpatterns.sec05.service;

import com.vinsguru.webfluxpatterns.sec05.dto.reservation.ReservationItemRequest;
import com.vinsguru.webfluxpatterns.sec05.dto.reservation.ReservationItemResponse;
import com.vinsguru.webfluxpatterns.sec05.dto.reservation.ReservationType;
import reactor.core.publisher.Flux;

public abstract class AbstractReservationHandler {

    protected abstract ReservationType getType();
    protected abstract Flux<ReservationItemResponse> reserve(Flux<ReservationItemRequest> flux);
}
