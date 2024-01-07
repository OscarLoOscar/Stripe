package com.example.shoppingcart.Payment.Config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import com.stripe.Stripe;
import jakarta.annotation.PostConstruct;

@ConfigurationProperties(prefix = "stripe")
public record StripeConfig(String secretKey, //
    String publishableKey, //
    String webHookSecretKey, //
    String currency) {

  @PostConstruct
  private void init() {
    setStripeKey(secretKey);
  }

  public static synchronized void setStripeKey(String stripeKey) {
    Stripe.apiKey = stripeKey;
  }
}
