package com.demo.oops.controller;

import com.demo.oops.service.OopsHealthIndicator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final OopsHealthIndicator oopsHealthIndicator;

    @PutMapping("/update-health")
    public ResponseEntity<Object> updateHealth() {
        try {
            oopsHealthIndicator.changeHealth();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Something went wrong : " + ex.getMessage());
        }
        return ResponseEntity.ok(true);
    }
}
