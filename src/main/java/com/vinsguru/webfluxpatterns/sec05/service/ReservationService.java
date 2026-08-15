package com.vinsguru.webfluxpatterns.sec05.service;

import com.vinsguru.webfluxpatterns.sec05.dto.reservation.ReservationItemRequest;
import com.vinsguru.webfluxpatterns.sec05.dto.reservation.ReservationItemResponse;
import com.vinsguru.webfluxpatterns.sec05.dto.reservation.ReservationResponse;
import com.vinsguru.webfluxpatterns.sec05.dto.reservation.ReservationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final Map<ReservationType, AbstractReservationHandler> map;

    public ReservationService(List<AbstractReservationHandler> list) {
        this.map = list.stream().collect(Collectors.toMap(
                AbstractReservationHandler::getType, Function.identity()
        ));
    }

    public Mono<ReservationResponse> reserve(Flux<ReservationItemRequest> flux) {
        return flux.groupBy(ReservationItemRequest::type)
                .flatMap(this::aggregate)
                .collectList()
                .map(this::toResponse);
    }

    private Flux<ReservationItemResponse> aggregate(GroupedFlux<ReservationType, ReservationItemRequest> groupedFlux) {
        var key = groupedFlux.key();
        var handler = map.get(key);
        return handler.reserve(groupedFlux);
    }

    private ReservationResponse toResponse(List<ReservationItemResponse> items) {
        return ReservationResponse.builder()
                .reservationId(UUID.randomUUID())
                .price(items.stream().mapToInt(ReservationItemResponse::price).sum())
                .items(items)
                .build();
    }

}
