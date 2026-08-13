package com.vinsguru.webfluxpatterns.sec04.controller;

import com.vinsguru.webfluxpatterns.sec04.dto.request.OrderRequest;
import com.vinsguru.webfluxpatterns.sec04.dto.request.OrderResponse;
import com.vinsguru.webfluxpatterns.sec04.service.OrchestratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrchestratorService orchestratorService;

    @PostMapping()
    public Mono<ResponseEntity<OrderResponse>> placeOrder(@RequestBody Mono<OrderRequest> request) {
        return this.orchestratorService.placeOrder(request)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


}
