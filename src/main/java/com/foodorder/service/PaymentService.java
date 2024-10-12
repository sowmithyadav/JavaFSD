package com.foodorder.service;

import com.foodorder.model.Order;
import com.stripe.Stripe;
import com.stripe.model.Charge;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    private static final String API_SECRET_KEY = "your-stripe-api-key";

    public String processPayment(Order order) throws Exception {
        // Stripe logic for payment
        Stripe.apiKey = API_SECRET_KEY;

        Map<String, Object> params = new HashMap<>();
        params.put("amount", (int)(order.getTotalPrice() * 100)); // converting to cents
        params.put("currency", "usd");
        params.put("description", "Order Payment");
        params.put("source", "tok_visa"); // Use token from front-end

        Charge charge = Charge.create(params);
        return charge.getId();
    }
}
