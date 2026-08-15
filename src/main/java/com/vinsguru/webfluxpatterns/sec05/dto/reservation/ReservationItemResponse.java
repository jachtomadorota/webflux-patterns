package com.vinsguru.webfluxpatterns.sec05.dto.reservation;

import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record ReservationItemResponse(ReservationType type,
                                      String category,
                                      String city,
                                      LocalDate from,
                                      LocalDate to,
                                      UUID itemId,
                                      Integer price) {
}
