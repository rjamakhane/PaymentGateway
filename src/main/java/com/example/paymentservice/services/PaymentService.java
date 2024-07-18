package com.example.paymentservice.services;

import com.example.paymentservice.paymentgateways.PaymentGateway;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private PaymentGateway paymentGateway;

    public PaymentService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public String createPaymentLink(Long orderId, Long amount, String phonenumber) throws StripeException, RazorpayException {
        // call the razorpay api or Stripe api to create a payment link
//        orderId = 123L;
        return paymentGateway.createPaymentLink(orderId, amount, phonenumber);
    }
}
