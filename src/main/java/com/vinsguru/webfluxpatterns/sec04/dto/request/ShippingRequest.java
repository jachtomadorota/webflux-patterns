package com.vinsguru.webfluxpatterns.sec04.dto.request;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ShippingRequest(Integer quantity,
                              UUID inventoryId,
                              Integer userId,
                              UUID orderId) {
}
