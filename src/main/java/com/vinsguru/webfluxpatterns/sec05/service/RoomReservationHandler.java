package com.vinsguru.webfluxpatterns.sec05.service;

import com.vinsguru.webfluxpatterns.sec05.client.RoomClient;
import com.vinsguru.webfluxpatterns.sec05.dto.reservation.ReservationItemRequest;
import com.vinsguru.webfluxpatterns.sec05.dto.reservation.ReservationItemResponse;
import com.vinsguru.webfluxpatterns.sec05.dto.reservation.ReservationType;
import com.vinsguru.webfluxpatterns.sec05.dto.room.RoomCategory;
import com.vinsguru.webfluxpatterns.sec05.dto.room.RoomReservationRequest;
import com.vinsguru.webfluxpatterns.sec05.dto.room.RoomReservationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class RoomReservationHandler extends AbstractReservationHandler {

    private final RoomClient roomClient;

    @Override
    protected ReservationType getType() {
        return ReservationType.ROOM;
    }

    @Override
    protected Flux<ReservationItemResponse> reserve(Flux<ReservationItemRequest> flux) {
        return flux.map(this::toRoomRequest)
                .transform(this.roomClient::reserve)
                .map(this::toResponse);
    }

    private RoomReservationRequest toRoomRequest(ReservationItemRequest request) {
        return RoomReservationRequest.builder()
                .city(request.city())
                .checkIn(request.to())
                .checkOut(request.to())
                .category(RoomCategory.valueOf(request.category()))
                .build();
    }

    private ReservationItemResponse toResponse(RoomReservationResponse reservationResponse) {
        return ReservationItemResponse.builder()
                .city(reservationResponse.city())
                .from(reservationResponse.checkIn())
                .to(reservationResponse.checkOut())
                .category(reservationResponse.city())
                .price(reservationResponse.price())
                .type(getType())
                .build();
    }
}
