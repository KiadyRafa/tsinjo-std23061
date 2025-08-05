package com.tsinjo.demo.service;

import com.tsinjo.demo.domain.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.springframework.beans.factory.annotation.Value;

import java.time.Duration;

@Service
public class VolaService {
    private static final Logger LOG = LoggerFactory.getLogger(VolaService.class);
    private final WebClient webClient;

    @Value("${vola.api-key}")
    private String volaApiKey;

    public VolaService(WebClient.Builder builder) {
        this.webClient = builder
            .baseUrl("https://42cwka3n4ifcp7ufheyrpmph240iuaxo.lambda-url.eu-west-3.on.aws")
            .build();
    }

    @Async
    public void verifyPaymentAsync(Payment payment) {
        Mono.delay(Duration.ofSeconds(5)).repeat().flatMap(tick ->
            webClient.get()
                .uri("/v3/payment/" + payment.id())
                .header("x-api-key", volaApiKey) // ✅ Header correct ici
                .retrieve()
                .bodyToMono(String.class)
                .doOnNext(response -> LOG.info("Checked payment {}: {}", payment.id(), response))
        ).subscribe();
    }
}
