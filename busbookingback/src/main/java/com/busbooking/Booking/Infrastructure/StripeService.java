package com.busbooking.Booking.Infrastructure;

import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
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
            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("amount", amount);
            chargeParams.put("currency", "eur");
            chargeParams.put("description", "Charge for " + email);
            chargeParams.put("source", token);

            Charge charge = Charge.create(chargeParams);
            id = charge.getId();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return id;
    }

    public String createCustomer(String email, String token) {
        String id = null;
        try {
            Stripe.apiKey = this.API_KEY;
            Map<String, Object> customerParams = new HashMap<>();
            customerParams.put("id", email);
            customerParams.put("description", "Customer for " + email);
            customerParams.put("email", email);
            customerParams.put("source", token);

            Customer customer = Customer.create(customerParams);
            id = customer.getId();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return id;
    }


}
