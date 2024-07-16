package com.example.paymentservice.paymentgateways;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class RazorPayPaymentGateway implements PaymentGateway {
    private RazorpayClient razorpay;
    public  RazorPayPaymentGateway(RazorpayClient razorpay){
        this.razorpay = razorpay;
    }
    @Override
    public String createPaymentLink(Long orderId) throws RazorpayException {
        // call the razorpay api to create a payment link
        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount",1000); //1000 will be 10.00 as last two digits are considered as decimal
        paymentLinkRequest.put("currency","INR");
//        paymentLinkRequest.put("accept_partial",true);
//        paymentLinkRequest.put("first_min_partial_amount",100);
        long currentTimePlusTenMinutesInMillis = System.currentTimeMillis() + (20 * 60 * 1000);
        paymentLinkRequest.put("expire_by",currentTimePlusTenMinutesInMillis);
        paymentLinkRequest.put("reference_id",orderId.toString());
        paymentLinkRequest.put("description","Payment for orderId "+orderId.toString());
        JSONObject customer = new JSONObject();
        customer.put("name", "8747074498");
        customer.put("contact","Deepak Kasera");
        customer.put("email","deepak.kasera@scaler.com");
        paymentLinkRequest.put("customer",customer);
        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("notify",notify);
        paymentLinkRequest.put("reminder_enable",true);
        JSONObject notes = new JSONObject();
        notes.put("policy_name","Scaler course");
        paymentLinkRequest.put("notes",notes);
        paymentLinkRequest.put("callback_url","https://www.scaler.com/academy/mentee-dashboard/todos");
        paymentLinkRequest.put("callback_method","get");

        try {
            PaymentLink payment = razorpay.paymentLink.create(paymentLinkRequest);
            // Assuming a successful response, parse it
            System.out.println("Payment Link Created: " + payment.toString());
            return payment.get("short_url").toString();
        } catch (RazorpayException e) {
            // Handle API errors here
            System.err.println("Razorpay API Error: " + e.getMessage());
        }
        return null;
    }
}
