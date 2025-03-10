package com.example.dashboard.payment.controller;

import com.example.dashboard.payment.dto.ReceivedRequestDto;
import com.example.dashboard.payment.sevice.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/payment")
    public String showReceiveForm(Model model) {
        model.addAttribute("receivedRequest", new ReceivedRequestDto());
        return "payment";
    }

    @PostMapping("/payment")
    public String processReceive(@ModelAttribute ReceivedRequestDto receivedRequest) {
        paymentService.savePayment(receivedRequest);
        return "redirect:/dashboard";
    }
}
