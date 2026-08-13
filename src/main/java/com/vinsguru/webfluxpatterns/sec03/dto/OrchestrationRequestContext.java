package com.vinsguru.webfluxpatterns.sec03.dto;

import com.vinsguru.webfluxpatterns.sec03.dto.request.InventoryRequest;
import com.vinsguru.webfluxpatterns.sec03.dto.request.OrderRequest;
import com.vinsguru.webfluxpatterns.sec03.dto.request.PaymentRequest;
import com.vinsguru.webfluxpatterns.sec03.dto.request.ShippingRequest;
import com.vinsguru.webfluxpatterns.sec03.dto.response.InventoryResponse;
import com.vinsguru.webfluxpatterns.sec03.dto.response.PaymentResponse;
import com.vinsguru.webfluxpatterns.sec03.dto.response.ShippingResponse;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
public class OrchestrationRequestContext {

    private int count;
    private final UUID orderId = UUID.randomUUID();
    private Status status;
    private OrderRequest orderRequest;
    private Integer productPrice;
    private PaymentRequest paymentRequest;
    private PaymentResponse paymentResponse;
    private InventoryResponse inventoryResponse;
    private InventoryRequest inventoryRequest;
    private ShippingRequest shippingRequest;
    private ShippingResponse shippingResponse;

    public OrchestrationRequestContext(OrderRequest orderRequest) {
        this.orderRequest = orderRequest;
    }
}
