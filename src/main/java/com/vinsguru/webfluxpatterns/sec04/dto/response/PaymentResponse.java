package com.vinsguru.webfluxpatterns.sec04.dto.response;

import com.vinsguru.webfluxpatterns.sec04.dto.Status;
import lombok.Builder;

import java.util.UUID;

@Builder
public record PaymentResponse(Integer userId,
                              UUID paymentId,
                              String name,
                              Integer balance,
                              Status status) {
}
