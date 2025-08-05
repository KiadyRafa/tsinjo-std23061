package com.tsinjo.demo.domain;

import java.time.Instant;

public record Payment(
    String id,
    Instant date,
    double amount,
    String paymentMethod,
    String status
) {}