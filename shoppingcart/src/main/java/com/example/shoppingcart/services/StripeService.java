package com.example.shoppingcart.services;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.example.shoppingcart.model.ChargeRequest;
import com.example.shoppingcart.services.impl.StripeServiceImpl;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

@Service
public class StripeService implements StripeServiceImpl {
  @Value("${stripe.apikey}") // Add your Stripe secret key to application.properties or application.yml
  private String secretKey;

  public Charge createPaymentSession(Long userId, ChargeRequest chargeRequest)
      throws StripeException {
    Stripe.apiKey = secretKey;

    Map<String, Object> chargeParams = new HashMap<>();
    chargeParams.put("amount", chargeRequest.getAmount());
    chargeParams.put("currency", chargeRequest.getCurrency());
    chargeParams.put("description", chargeRequest.getDescription());
    chargeParams.put("source", chargeRequest.getToken());

    return Charge.create(chargeParams);
  }
}


