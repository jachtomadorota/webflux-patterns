package com.vinsguru.webfluxpatterns.sec05.dto.reservation;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record ReservationResponse(UUID reservationId,
                                  Integer price,
                                  List<ReservationItemResponse> items) {
}
