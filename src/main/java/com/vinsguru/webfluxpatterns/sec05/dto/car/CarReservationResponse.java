package com.vinsguru.webfluxpatterns.sec05.dto.car;

import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record CarReservationResponse(CarCategory carCategory,
                                     String city,
                                     LocalDate drop,
                                     LocalDate pickup,
                                     Integer price,
                                     UUID reservationId) {
}
