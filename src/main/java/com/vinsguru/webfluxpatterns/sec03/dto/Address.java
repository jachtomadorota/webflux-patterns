package com.vinsguru.webfluxpatterns.sec03.dto;

import lombok.Builder;

@Builder
public record Address(String city,
                      String state,
                      String street,
                      String zipCode) {
}
