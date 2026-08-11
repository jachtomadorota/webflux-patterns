package com.vinsguru.webfluxpatterns.sec01.dto;

import lombok.Builder;

@Builder
public record Review(String comment,
                     Integer id,
                     Integer rating,
                     String user) {
}
