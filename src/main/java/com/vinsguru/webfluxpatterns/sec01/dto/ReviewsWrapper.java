package com.vinsguru.webfluxpatterns.sec01.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record ReviewsWrapper(List<Review> reviews) {
}
