package com.vinsguru.webfluxpatterns.sec04.util;

import com.vinsguru.webfluxpatterns.sec04.dto.OrchestrationRequestContext;
import com.vinsguru.webfluxpatterns.sec04.dto.request.InventoryRequest;
import com.vinsguru.webfluxpatterns.sec04.dto.request.PaymentRequest;
import com.vinsguru.webfluxpatterns.sec04.dto.request.ShippingRequest;

public class OrchestrationUtil {

    public static void buildRequestContext(OrchestrationRequestContext ctx) {
        buildInventoryRequest(ctx);
        buildPaymentRequest(ctx);
        buildShippingRequest(ctx);

    }

    public static void buildPaymentRequest(OrchestrationRequestContext ctx) {
        var paymentRequest = PaymentRequest.builder()
                .orderId(ctx.getOrderId())
                .amount(ctx.getProductPrice() * ctx.getOrderRequest().quantity())
                .userId(ctx.getOrderRequest().userId())
                .build();
        ctx.setPaymentRequest(paymentRequest);
    }

    public static void buildInventoryRequest(OrchestrationRequestContext ctx) {
        var inventoryRequest = InventoryRequest.builder()
                .productId(ctx.getOrderRequest().productId())
                .quantity(ctx.getOrderRequest().quantity())
                .build();
        ctx.setInventoryRequest(inventoryRequest);
    }


    public static void buildShippingRequest(OrchestrationRequestContext ctx) {
        var shippingRequest = ShippingRequest.builder()
                .orderId(ctx.getOrderId())
                .inventoryId(ctx.getInventoryResponse().inventoryId())
                .userId(ctx.getOrderRequest().userId())
                .quantity(ctx.getOrderRequest().quantity())
                .build();
        ctx.setShippingRequest(shippingRequest);
    }


}
