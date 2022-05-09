package com.busbooking.Booking.Infrastructure;

import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {
    private String API_KEY = "sk_test_51KxUWWGEK9krOIWf3qPHtA8FqsDheJKOgUEIE4uFPlmtFn8JvIanQUHHniA0jV9rnn9olvRhohakTCMR9AhfDjEO00bHsF8uDk";

    public String createCharge(String email, String token, int amount) {
        String id = null;
        try {
            Stripe.apiKey = this.API_KEY;
            Map<String, Object> params = new HashMap<>();
            params.put("amount", amount);
            params.put("currency", "eur");
            params.put("payment_method", token);
            params.put("confirm", true);
            PaymentIntent payment = PaymentIntent.create(params);

            id = payment.getId();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return id;
    }




}
