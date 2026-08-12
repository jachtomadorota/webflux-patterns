package com.vinsguru.webfluxpatterns.sec03.dto;

public record User(Address address,
                   Double balance,
                   String name,
                   Integer userId) {
}
