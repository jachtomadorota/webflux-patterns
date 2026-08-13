package com.vinsguru.webfluxpatterns.sec04.dto.response;

import com.vinsguru.webfluxpatterns.sec04.dto.Address;
import com.vinsguru.webfluxpatterns.sec04.dto.Status;
import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record ShippingResponse(UUID shippingId,
                               Integer quantity,
                               Status status,
                               LocalDate expectedDelivery,
                               Address address) {
}
