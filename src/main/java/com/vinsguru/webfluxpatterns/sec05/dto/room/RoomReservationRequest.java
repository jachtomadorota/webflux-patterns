package com.vinsguru.webfluxpatterns.sec05.dto.room;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record RoomReservationRequest(String city,
                                     LocalDate checkIn,
                                     LocalDate checkOut,
                                     RoomCategory category) {
}
