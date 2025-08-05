package com.tsinjo.demo.domain;

public record Donation(
    Donor donor,
    Payment payment
) {}