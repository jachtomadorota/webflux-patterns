package com.vinsguru.webfluxpatterns.sec03.service;

import com.vinsguru.webfluxpatterns.sec03.dto.OrchestrationRequestContext;
import com.vinsguru.webfluxpatterns.sec03.dto.Status;
import com.vinsguru.webfluxpatterns.sec03.service.orchestrator.Orchestrator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderFulfillmentService {

    private final List<Orchestrator> orchestrators;

    public Mono<OrchestrationRequestContext> placeOrder(OrchestrationRequestContext ctx) {
        List<Mono<OrchestrationRequestContext>> list = orchestrators.stream()
                .map(o -> o.create(ctx))
                .toList();
        return Mono.zip(list, a -> a[0])
                .cast(OrchestrationRequestContext.class);
    }

    private void updateStatus(OrchestrationRequestContext ctx) {
        boolean allSuccess = this.orchestrators.stream().allMatch(o -> o.isSuccess().test(ctx));
        Status status = allSuccess ? Status.SUCCESS : Status.FAILED;
        ctx.setStatus(status);
    }
}
