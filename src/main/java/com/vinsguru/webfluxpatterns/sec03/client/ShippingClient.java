package com.vinsguru.webfluxpatterns.sec03.client;

import com.vinsguru.webfluxpatterns.sec03.dto.Status;
import com.vinsguru.webfluxpatterns.sec03.dto.User;
import com.vinsguru.webfluxpatterns.sec03.dto.request.PaymentRequest;
import com.vinsguru.webfluxpatterns.sec03.dto.request.ShippingRequest;
import com.vinsguru.webfluxpatterns.sec03.dto.response.PaymentResponse;
import com.vinsguru.webfluxpatterns.sec03.dto.response.ShippingResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ShippingClient {

    private final static String SCHEDULE_ENDPOINT = "schedule";
    private final static String CANCEL_ENDPOINT = "cancel";
    private final WebClient webClient;

    public ShippingClient(@Value("${sec03.shipping.service}") String baseUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }



    public Mono<ShippingResponse> schedule(ShippingRequest request) {
        return callUserService(request, SCHEDULE_ENDPOINT);

    }

    public Mono<ShippingResponse> cancel(ShippingRequest request) {
        return callUserService(request, CANCEL_ENDPOINT);
    }

    private Mono<ShippingResponse> callUserService(ShippingRequest request, String endpoint) {
        return this.webClient.post()
                .uri(endpoint)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ShippingResponse.class)
                .onErrorReturn(buildErrorResponse(request));
    }

    private ShippingResponse buildErrorResponse(ShippingRequest request) {
        return ShippingResponse.builder()
                .orderId(request.orderId())
                .quantity(request.quantity())
                .status(Status.FAILED)
                .build();


    }
}
