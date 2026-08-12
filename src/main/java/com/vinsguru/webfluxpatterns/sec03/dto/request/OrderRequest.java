package com.vinsguru.webfluxpatterns.sec03.dto.request;

import lombok.Builder;

@Builder
public record OrderRequest(Integer userId,
                           Integer productId,
                           Integer quantity) {
}
