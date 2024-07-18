package com.example.paymentservice.paymentgateways;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentGateway {
    String createPaymentLink(Long orderId, Long amount, String phonenumber) throws StripeException, RazorpayException;
}
