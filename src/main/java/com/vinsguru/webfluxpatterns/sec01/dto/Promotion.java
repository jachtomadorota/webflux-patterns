package com.vinsguru.webfluxpatterns.sec01.dto;

import java.time.LocalDate;

public record Promotion(Integer discount,
                        LocalDate endDate,
                        Integer id,
                        String type) {
}
