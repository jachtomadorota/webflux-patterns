package com.vinsguru.webfluxpatterns.sec03.dto.request;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ShippingRequest(Integer quantity,
                              Integer userId,
                              UUID orderId) {
}
