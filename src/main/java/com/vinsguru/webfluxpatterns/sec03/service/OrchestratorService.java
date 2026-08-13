package com.vinsguru.webfluxpatterns.sec03.service;

import com.vinsguru.webfluxpatterns.sec03.client.ProductClient;
import com.vinsguru.webfluxpatterns.sec03.dto.OrchestrationRequestContext;
import com.vinsguru.webfluxpatterns.sec03.dto.request.OrderRequest;
import com.vinsguru.webfluxpatterns.sec03.dto.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class OrchestratorService {


    private final ProductClient productClient;
    private final OrderFulfillmentService fulfillmentService;
    private final OrderCancellationService cancellationService;


    public void placeOrder(Mono<OrderRequest> request) {
        request.map(OrchestrationRequestContext::new);
    }

    private Mono<OrchestrationRequestContext> getProduct(OrchestrationRequestContext ctx) {
        return this.productClient.getProductById(ctx.getOrderRequest().productId())
                .map(Product::price)
                .doOnNext(ctx::setProductPrice)
                .thenReturn(ctx);
    }
}
