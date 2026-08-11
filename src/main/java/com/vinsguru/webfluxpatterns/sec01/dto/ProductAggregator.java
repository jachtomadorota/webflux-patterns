package com.vinsguru.webfluxpatterns.sec01.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ProductAggregator(Integer id,
                                String category,
                                String description,
                                Price price,
                                LocalDate endDate,
                                ReviewsWrapper reviews) {
}
