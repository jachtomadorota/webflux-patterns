package com.vinsguru.webfluxpatterns.sec05.dto.car;

import java.time.LocalDate;
import java.util.UUID;

public record CarReservationResponse(CarCategory carCategory,
                                     String city,
                                     LocalDate drop,
                                     LocalDate pickup,
                                     Integer price,
                                     UUID reservationId) {
}
