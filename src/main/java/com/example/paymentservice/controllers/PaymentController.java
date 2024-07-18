package com.example.paymentservice.controllers;

import com.example.paymentservice.dtos.CreatePaymentLinkRequestDto;
import com.example.paymentservice.services.PaymentService;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/payments")
public class PaymentController {

    PaymentService paymentService;
    PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/")
    public ResponseEntity<String> createPaymentLink(@RequestBody CreatePaymentLinkRequestDto createPaymentLinkRequestDto) throws StripeException, RazorpayException {
        String paymentLink= paymentService.createPaymentLink(createPaymentLinkRequestDto.getOrderId(), createPaymentLinkRequestDto.getAmount(), createPaymentLinkRequestDto.getPhoneNumber());
        return new ResponseEntity<>(paymentLink, HttpStatus.OK);
    }
}
