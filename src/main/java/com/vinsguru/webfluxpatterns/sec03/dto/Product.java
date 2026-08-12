package com.vinsguru.webfluxpatterns.sec03.dto;


import lombok.Builder;

@Builder
public record Product(Integer id,
                      String category,
                      String description,
                      Integer price) {
}
