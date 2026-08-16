package com.vinsguru.webfluxpatterns.sec08.config;

import io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class CircuitBreakerConfig {


    @Bean
    public CircuitBreakerConfigCustomizer reviewService() {
        return CircuitBreakerConfigCustomizer.of("review-service",
                cb -> cb.minimumNumberOfCalls(4)
                        .maxWaitDurationInHalfOpenState(Duration.ofSeconds(3))
                        .build());
    }
}
