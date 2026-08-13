package com.vinsguru.webfluxpatterns.sec04.client;

import com.vinsguru.webfluxpatterns.sec04.dto.Status;
import com.vinsguru.webfluxpatterns.sec04.dto.User;
import com.vinsguru.webfluxpatterns.sec04.dto.request.PaymentRequest;
import com.vinsguru.webfluxpatterns.sec04.dto.response.PaymentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class UserClient {

    private final static String REFUND_ENDPOINT = "refund";
    private final static String DEDUCT_ENDPOINT = "deduct";
    private final WebClient webClient;

    public UserClient(@Value("${sec04.user.service}") String baseUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public Mono<User> getUserById(Integer id) {
        return this.webClient.get()
                .uri("{id}", id)
                .retrieve()
                .bodyToMono(User.class)
                .onErrorResume(ex -> Mono.empty());
    }

    public Mono<PaymentResponse> deduct(PaymentRequest request) {
        return callUserService(request, DEDUCT_ENDPOINT);

    }

    public Mono<PaymentResponse> refund(PaymentRequest request) {
        return callUserService(request, REFUND_ENDPOINT);
    }

    private Mono<PaymentResponse> callUserService(PaymentRequest request, String endpoint) {
        return this.webClient.post()
                .uri(endpoint)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(PaymentResponse.class)
                .onErrorReturn(buildErrorResponse(request));
    }

    private PaymentResponse buildErrorResponse(PaymentRequest request) {
        return PaymentResponse.builder()
                .userId(request.userId())
                .balance(request.amount())
                .status(Status.FAILED)
                .build();


    }


}
