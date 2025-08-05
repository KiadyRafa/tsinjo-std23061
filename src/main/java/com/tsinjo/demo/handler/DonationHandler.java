
package com.tsinjo.demo.handler;

import com.tsinjo.demo.domain.*;
import com.tsinjo.demo.service.VolaService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class DonationHandler {

    private final List<Donation> donations = new ArrayList<>();
    private final List<Help> helps = new ArrayList<>();
    private final VolaService volaService;

    public DonationHandler(VolaService volaService) {
        this.volaService = volaService;
        mockHelps();
    }

    public void submitDonation(String email, String name, double amount, String paymentMethod) {
        String paymentId = UUID.randomUUID().toString();
        Payment payment = new Payment(paymentId, Instant.now(), amount, paymentMethod, "VERIFYING");
        Donation donation = new Donation(new Donor(email, name), payment);
        donations.add(donation);
        volaService.verifyPaymentAsync(payment);
    }

    public List<Donation> getAllDonations() {
        return Collections.unmodifiableList(donations);
    }

    public List<Help> getAllHelps() {
        return Collections.unmodifiableList(helps);
    }

    private void mockHelps() {
        helps.add(new Help(
            new Beneficiary("help@email.com", "Beneficiaire Exemple"),
            new Payment(UUID.randomUUID().toString(), Instant.now(), 100.0, "MobileMoney", "SUCCEEDED"),
            "Accident grave de moto"
        ));
    }
}