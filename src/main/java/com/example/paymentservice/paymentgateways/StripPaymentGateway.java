package com.example.paymentservice.paymentgateways;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class StripPaymentGateway implements PaymentGateway{
    @Value("${stripe.secret}")
    private String stripeApiKey;
    @Override
    public String createPaymentLink(Long orderId, Long amount, String phoneNumber) throws StripeException {
        Stripe.apiKey = stripeApiKey;

        PriceCreateParams priceCreateParams =
                PriceCreateParams.builder()
                        .setCurrency("inr")
                        .setUnitAmount(amount)
                        .setProductData(
                                PriceCreateParams.ProductData.builder().setName("iPhone 15").build()
                        )
                        .build();
        Price price = Price.create(priceCreateParams);



        PaymentLinkCreateParams paymentLinkCreateParams =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .setAfterCompletion(
                                PaymentLinkCreateParams.AfterCompletion.builder()
                                        .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT) // Callback
                                        .setRedirect(PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                                .setUrl("https://www.scaler.com/academy/instructor-dashboard/")
                                                .build()
                                        )
                                        .build()

                        )
                        .build();

        PaymentLink paymentLink = PaymentLink.create(paymentLinkCreateParams);

        return paymentLink.toString();
    }
}
