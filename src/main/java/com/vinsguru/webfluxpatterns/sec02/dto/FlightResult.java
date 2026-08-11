package com.vinsguru.webfluxpatterns.sec02.dto;

import java.time.LocalDate;

public record FlightResult(String airline,
                           LocalDate date,
                           String from,
                           Integer price,
                           String to) {


    public FlightResult withToAndFromAndAirline(String to, String from, String airline){
        return new FlightResult(airline, this.date, from, this.price, to);
    }
}
