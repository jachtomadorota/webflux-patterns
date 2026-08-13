package com.vinsguru.webfluxpatterns.sec04.dto.response;

import com.vinsguru.webfluxpatterns.sec04.dto.Status;
import lombok.Builder;

import java.util.UUID;

@Builder
public record InventoryResponse(Integer productId,
                                UUID inventoryId,
                                Integer quantity,
                                Integer remainingQuantity,
                                Status status) {
}
