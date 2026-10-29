package example.com.cilab1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class HealthController {

    @Value("${CHAOS_MODE:false}")
    private boolean chaosMode;

    @Value("${chaos.failure-rate:0.3}")
    private double failureRate;

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        if (chaosMode && ThreadLocalRandom.current().nextDouble() < failureRate) {
            return ResponseEntity.internalServerError().body(Map.of("status", "error"));
        }
        return ResponseEntity.ok(Map.of("status", "ok"));
    }
}