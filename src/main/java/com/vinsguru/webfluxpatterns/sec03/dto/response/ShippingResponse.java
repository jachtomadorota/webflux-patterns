package com.vinsguru.webfluxpatterns.sec03.dto.response;

import com.vinsguru.webfluxpatterns.sec03.dto.Address;
import com.vinsguru.webfluxpatterns.sec03.dto.Status;
import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record ShippingResponse(UUID orderId,
                               Integer quantity,
                               Status status,
                               LocalDate expectedDelivery,
                               Address address) {
}
