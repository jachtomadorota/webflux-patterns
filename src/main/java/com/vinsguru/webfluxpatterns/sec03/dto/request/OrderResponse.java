package com.vinsguru.webfluxpatterns.sec03.dto.request;

import com.vinsguru.webfluxpatterns.sec03.dto.Address;
import com.vinsguru.webfluxpatterns.sec03.dto.Status;
import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record OrderResponse(Integer userId,
                            Integer productId,
                            UUID orderId,
                            Status status,
                            Address address,
                            LocalDate expectedDelivery) {
}
