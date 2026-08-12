package com.vinsguru.webfluxpatterns.sec03.dto;

public record Address(String city,
                      String state,
                      String street,
                      String zipCode) {
}
