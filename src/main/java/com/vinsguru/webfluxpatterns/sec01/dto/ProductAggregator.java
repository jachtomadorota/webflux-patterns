package com.vinsguru.webfluxpatterns.sec01.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record ProductAggregator(Integer id,
                                String category,
                                String description,
                                Price price,
                                LocalDate endDate,
                                List<Review> reviews) {
}
