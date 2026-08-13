package com.vinsguru.webfluxpatterns.sec04.dto.request;

import lombok.Builder;

@Builder
public record OrderRequest(Integer userId,
                           Integer productId,
                           Integer quantity) {
}
