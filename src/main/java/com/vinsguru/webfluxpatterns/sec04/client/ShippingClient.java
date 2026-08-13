package com.vinsguru.webfluxpatterns.sec04.client;

import com.vinsguru.webfluxpatterns.sec04.dto.Status;
import com.vinsguru.webfluxpatterns.sec04.dto.request.ShippingRequest;
import com.vinsguru.webfluxpatterns.sec04.dto.response.ShippingResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ShippingClient {

    private final static String SCHEDULE_ENDPOINT = "schedule";
    private final static String CANCEL_ENDPOINT = "cancel";
    private final WebClient webClient;

    public ShippingClient(@Value("${sec04.shipping.service}") String baseUrl) {
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
                .quantity(request.quantity())
                .status(Status.FAILED)
                .build();


    }
}
