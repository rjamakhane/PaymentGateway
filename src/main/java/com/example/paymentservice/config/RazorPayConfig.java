package com.example.paymentservice.config;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RazorPayConfig {
    @Value("${razorpay.key.id}")
    private String razaorPayKeyId;
    @Value("${razorpay.secret}")
    private String razorPaySecret;
    @Bean
    public RazorpayClient razorpayClient() throws RazorpayException {
         return new RazorpayClient(razaorPayKeyId, razorPaySecret);
    }
}
