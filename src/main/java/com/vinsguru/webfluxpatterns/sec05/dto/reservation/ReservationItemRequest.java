package com.vinsguru.webfluxpatterns.sec05.dto.reservation;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ReservationItemRequest (ReservationType type,
                                      String category,
                                      String city,
                                      LocalDate from,
                                      LocalDate to) {
}
