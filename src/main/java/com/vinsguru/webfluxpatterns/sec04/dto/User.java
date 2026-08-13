package com.vinsguru.webfluxpatterns.sec04.dto;

import lombok.Builder;

@Builder
public record User(Address address,
                   Double balance,
                   String name,
                   Integer userId) {
}
