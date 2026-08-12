package com.vinsguru.webfluxpatterns.sec03.dto;

import java.time.LocalDate;
import java.util.UUID;

public record OrderResponse(Integer userId,
                            Integer productId,
                            UUID orderId,
                            Status status,
                            Address address,
                            LocalDate expectedDelivery) {
}
