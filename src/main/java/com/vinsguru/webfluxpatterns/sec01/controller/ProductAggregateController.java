package com.vinsguru.webfluxpatterns.sec01.controller;

import com.vinsguru.webfluxpatterns.sec01.dto.ProductAggregator;
import com.vinsguru.webfluxpatterns.sec01.service.ProductAggregatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class ProductAggregateController {

    private final ProductAggregatorService aggregatorService;

    @GetMapping("/products/{id}")
    public Mono<ResponseEntity<ProductAggregator>> getProductAggregate(@PathVariable Integer id) {
        return aggregatorService.aggregateById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
