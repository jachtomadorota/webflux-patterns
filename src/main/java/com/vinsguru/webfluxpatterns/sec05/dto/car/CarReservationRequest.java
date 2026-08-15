package com.vinsguru.webfluxpatterns.sec05.dto.car;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CarReservationRequest(CarCategory carCategory,
                                    String city,
                                    LocalDate drop,
                                    LocalDate pickup) {
}
