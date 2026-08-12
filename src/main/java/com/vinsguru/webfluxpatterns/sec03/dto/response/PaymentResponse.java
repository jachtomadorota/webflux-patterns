package com.vinsguru.webfluxpatterns.sec03.dto.response;

import com.vinsguru.webfluxpatterns.sec03.dto.Status;
import lombok.Builder;

@Builder
public record PaymentResponse(Integer userId,
                              String name,
                              Integer balance,
                              Status status) {
}
