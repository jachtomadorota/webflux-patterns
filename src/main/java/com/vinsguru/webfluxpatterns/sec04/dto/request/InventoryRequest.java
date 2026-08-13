package com.vinsguru.webfluxpatterns.sec04.dto.request;

import lombok.Builder;

import java.util.UUID;

@Builder
public record InventoryRequest(UUID paymentId,
                               Integer productId,
                               Integer quantity) {
}
