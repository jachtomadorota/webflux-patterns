package com.vinsguru.webfluxpatterns.sec05.service;

import com.vinsguru.webfluxpatterns.sec05.client.CarClient;
import com.vinsguru.webfluxpatterns.sec05.dto.car.CarCategory;
import com.vinsguru.webfluxpatterns.sec05.dto.car.CarReservationRequest;
import com.vinsguru.webfluxpatterns.sec05.dto.car.CarReservationResponse;
import com.vinsguru.webfluxpatterns.sec05.dto.reservation.ReservationItemRequest;
import com.vinsguru.webfluxpatterns.sec05.dto.reservation.ReservationItemResponse;
import com.vinsguru.webfluxpatterns.sec05.dto.reservation.ReservationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class CarReservationHandler extends AbstractReservationHandler {

    private final CarClient carClient;


    @Override
    protected ReservationType getType() {
        return ReservationType.CAR;
    }

    @Override
    protected Flux<ReservationItemResponse> reserve(Flux<ReservationItemRequest> flux) {
        return flux.map(this::toCarRequest)
                .transform(this.carClient::reserve)
                .map(this::toResponse);
    }

    private CarReservationRequest toCarRequest(ReservationItemRequest request) {
        return CarReservationRequest.builder()
                .city(request.city())
                .drop(request.to())
                .pickup(request.to())
                .carCategory(CarCategory.valueOf(request.category()))
                .build();
    }

    private ReservationItemResponse toResponse(CarReservationResponse reservationResponse) {
        return ReservationItemResponse.builder()
                .city(reservationResponse.city())
                .from(reservationResponse.pickup())
                .to(reservationResponse.drop())
                .category(reservationResponse.city())
                .price(reservationResponse.price())
                .type(getType())
                .build();
    }
}
