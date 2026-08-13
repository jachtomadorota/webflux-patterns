package com.vinsguru.webfluxpatterns.sec03.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinsguru.webfluxpatterns.sec03.dto.OrchestrationRequestContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DebugUtil {

    public static void print(OrchestrationRequestContext ctx) {
        ObjectMapper mapper = new ObjectMapper();
        String value = null;
        try {
            value = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(ctx);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        log.info("{}", value);
    }
}
