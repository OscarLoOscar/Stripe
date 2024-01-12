package com.example.shoppingcart.stripePayment.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import com.stripe.Stripe;
import jakarta.annotation.PostConstruct;

// @Configuration
public class StripeConfig {
  @Value("${use.secret.key:false}")
  private boolean useSecretKey;

  @Value("${stripe.api.publicKey}")
  private String StripePublicKey;

  @Value("${stripe.api.secretKey}")
  private String StripeSecretKey;

  @PostConstruct
  @Conditional(UsePublicKeyCondition.class)
  public void initPublicKey() {
    Stripe.apiKey = StripePublicKey;
  }

  @PostConstruct
  @Conditional(UseSecretKeyCondition.class)
  public void initSecretKey() {
    Stripe.apiKey = StripeSecretKey;
  }

}
