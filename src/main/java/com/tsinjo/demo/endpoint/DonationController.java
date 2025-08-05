
package com.tsinjo.demo.endpoint;

import com.tsinjo.demo.domain.Donation;
import com.tsinjo.demo.handler.DonationHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DonationController {

    private final DonationHandler donationHandler;

    public DonationController(DonationHandler donationHandler) {
        this.donationHandler = donationHandler;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("donations", donationHandler.getAllDonations());
        model.addAttribute("helps", donationHandler.getAllHelps());
        return "index";
    }

    @PostMapping("/donation")
    public String submitDonation(@RequestParam String donorEmail,
                                  @RequestParam String donorName,
                                  @RequestParam double amount,
                                  @RequestParam String paymentMethod) {
        donationHandler.submitDonation(donorEmail, donorName, amount, paymentMethod);
        return "redirect:/";
    }
}