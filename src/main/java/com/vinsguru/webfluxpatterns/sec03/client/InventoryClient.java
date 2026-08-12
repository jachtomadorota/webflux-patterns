package com.vinsguru.webfluxpatterns.sec03.client;

import com.vinsguru.webfluxpatterns.sec03.dto.Status;
import com.vinsguru.webfluxpatterns.sec03.dto.request.InventoryRequest;
import com.vinsguru.webfluxpatterns.sec03.dto.response.InventoryResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class InventoryClient {

    private final static String RESTORE_ENDPOINT = "restore";
    private final static String DEDUCT_ENDPOINT = "deduct";
    private final WebClient webClient;

    public InventoryClient(@Value("${sec03.inventory.service}") String baseUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public Mono<Integer> getInventoryById(Integer id) {
        return this.webClient.get()
                .uri("{id}", id)
                .retrieve()
                .bodyToMono(Integer.class)
                .onErrorResume(ex -> Mono.empty());
    }

    public Mono<InventoryResponse> deduct(InventoryRequest request) {
        return callUserService(request, DEDUCT_ENDPOINT);

    }

    public Mono<InventoryResponse> restore(InventoryRequest request) {
        return callUserService(request, RESTORE_ENDPOINT);
    }

    private Mono<InventoryResponse> callUserService(InventoryRequest request, String endpoint) {
        return this.webClient.post()
                .uri(endpoint)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(InventoryResponse.class)
                .onErrorReturn(buildErrorResponse(request));
    }

    private InventoryResponse buildErrorResponse(InventoryRequest request) {
        return InventoryResponse.builder()
                .productId(request.productId())
                .quantity(request.quantity())
                .status(Status.FAILED)
                .build();


    }
}
