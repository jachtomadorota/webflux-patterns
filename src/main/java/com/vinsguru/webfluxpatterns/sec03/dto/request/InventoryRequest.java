package com.vinsguru.webfluxpatterns.sec03.dto.request;

import lombok.Builder;

import java.util.UUID;

@Builder
public record InventoryRequest(UUID orderId,
                               Integer productId,
                               Integer quantity) {
}
