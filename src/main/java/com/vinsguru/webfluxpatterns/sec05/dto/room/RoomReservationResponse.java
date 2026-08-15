package com.vinsguru.webfluxpatterns.sec05.dto.room;

import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record RoomReservationResponse(String city,
                                      LocalDate checkIn,
                                      LocalDate checkOut,
                                      RoomCategory category,
                                      UUID reservationId,
                                      Integer price) {
}
