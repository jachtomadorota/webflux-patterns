package com.vinsguru.webfluxpatterns.sec01.dto;


import lombok.Builder;

@Builder
public record Price(Integer price,
                    Double discount,
                    Double discountedPrice,
                    Double amountSaved) {
}
