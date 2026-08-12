package com.vinsguru.webfluxpatterns.sec03.util;

import com.vinsguru.webfluxpatterns.sec03.dto.OrchestrationRequestContext;
import com.vinsguru.webfluxpatterns.sec03.dto.request.InventoryRequest;
import com.vinsguru.webfluxpatterns.sec03.dto.request.PaymentRequest;
import com.vinsguru.webfluxpatterns.sec03.dto.request.ShippingRequest;

public class OrchestrationUtil {

    public static void buildRequestContext(OrchestrationRequestContext ctx) {
        buildInventoryRequest(ctx);
        buildPaymentRequest(ctx);
        buildShippingRequest(ctx);

    }

    private static void buildPaymentRequest(OrchestrationRequestContext ctx) {
        var paymentRequest = PaymentRequest.builder()
                .orderId(ctx.getOrderId())
                .amount(ctx.getProductPrice() * ctx.getOrderRequest().quantity())
                .userId(ctx.getOrderRequest().userId())
                .build();
        ctx.setPaymentRequest(paymentRequest);
    }

    private static void buildInventoryRequest(OrchestrationRequestContext ctx) {
        var inventoryRequest = InventoryRequest.builder()
                .orderId(ctx.getOrderId())
                .productId(ctx.getOrderRequest().productId())
                .quantity(ctx.getOrderRequest().quantity())
                .build();
        ctx.setInventoryRequest(inventoryRequest);
    }


    private static void buildShippingRequest(OrchestrationRequestContext ctx) {
        var shippingRequest = ShippingRequest.builder()
                .orderId(ctx.getOrderId())
                .userId(ctx.getOrderRequest().userId())
                .quantity(ctx.getOrderRequest().quantity())
                .build();
        ctx.setShippingRequest(shippingRequest);
    }


}
