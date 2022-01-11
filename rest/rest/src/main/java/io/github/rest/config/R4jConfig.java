package io.github.rest.config;


import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class R4jConfig {

    @Autowired
    private CircuitBreakerRegistry circuitBreakerRegistry;

    @PostConstruct
    private void postConstruct() {

        circuitBreakerRegistry.circuitBreaker("testConfig").getEventPublisher()
                .onStateTransition((event) -> {
                    if(event.getStateTransition().getFromState().equals(CircuitBreaker.State.CLOSED) &&
                            event.getStateTransition().getToState().equals(CircuitBreaker.State.OPEN)) {

                        log.info("Circuit breaker went from closed to open, disable digilocker for 80% users");
                    } else if(event.getStateTransition().getFromState().equals(CircuitBreaker.State.HALF_OPEN) &&
                            event.getStateTransition().getToState().equals(CircuitBreaker.State.CLOSED)) {

                        log.info("Circuit breaker went from half_open to open, enable digilocker for 100% users");
                    }
                });
    }
}