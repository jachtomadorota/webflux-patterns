package com.vinsguru.webfluxpatterns.sec05.dto.car;

import java.time.LocalDate;

public record CarReservationRequest(CarCategory carCategory,
                                    String city,
                                    LocalDate drop,
                                    LocalDate pickup) {
}
