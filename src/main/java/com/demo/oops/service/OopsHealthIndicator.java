package com.demo.oops.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class OopsHealthIndicator implements HealthIndicator {

    private boolean health = true;

    @Override
    public Health health() {
        if (health) {
            return Health
                    .up()
                    .withDetails(Map.of("health", health))
                    .build();
        } else {
            return Health.down().withDetail("Error Code", 123).build();
        }
    }

    public void changeHealth() {
        health = !health;
    }
}
