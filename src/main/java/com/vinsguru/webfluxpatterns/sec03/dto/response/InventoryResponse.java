package com.vinsguru.webfluxpatterns.sec03.dto.response;

import com.vinsguru.webfluxpatterns.sec03.dto.Status;
import lombok.Builder;

@Builder
public record InventoryResponse(Integer productId,
                                Integer quantity,
                                Integer remainingQuantity,
                                Status status) {
}
