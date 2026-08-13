package com.vinsguru.webfluxpatterns.sec04.service;

import com.vinsguru.webfluxpatterns.sec04.dto.OrchestrationRequestContext;
import com.vinsguru.webfluxpatterns.sec04.dto.Status;
import com.vinsguru.webfluxpatterns.sec04.dto.request.OrderRequest;
import com.vinsguru.webfluxpatterns.sec04.dto.request.OrderResponse;
import com.vinsguru.webfluxpatterns.sec04.util.DebugUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class OrchestratorService {


    private final OrderFulfillmentService fulfillmentService;
    private final OrderCancellationService cancellationService;


    public Mono<OrderResponse> placeOrder(Mono<OrderRequest> request) {
        return request.map(OrchestrationRequestContext::new)
                .flatMap(fulfillmentService::placeOrder)
                .doOnNext(this::doOrderPostProcessing)
                .doOnNext(DebugUtil::print)
                .map(this::toOrderResponse);
    }


    private void doOrderPostProcessing(OrchestrationRequestContext ctx) {
        if (Status.FAILED.equals(ctx.getStatus())) {
            this.cancellationService.cancelOrder(ctx);
        }
    }

    private OrderResponse toOrderResponse(OrchestrationRequestContext ctx) {
        boolean isSuccess = Status.SUCCESS.equals(ctx.getStatus());
        var address = isSuccess ? ctx.getShippingResponse().address() :  null;
        var expectedDelivery = isSuccess ? ctx.getShippingResponse().expectedDelivery() :  null;
        return OrderResponse.builder()
                .userId(ctx.getOrderRequest().userId())
                .orderId(ctx.getOrderId())
                .productId(ctx.getOrderRequest().productId())
                .status(ctx.getStatus())
                .address(address)
                .expectedDelivery(expectedDelivery)
                .build();


    }
}
