package com.tsinjo.demo.domain;

public record Help(
    Beneficiary beneficiary,
    Payment payment,
    String accidentDescription
) {}