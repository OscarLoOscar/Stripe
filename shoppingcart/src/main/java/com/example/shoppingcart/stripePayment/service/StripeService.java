package com.example.shoppingcart.stripePayment.service;

import com.example.shoppingcart.stripePayment.dto.StripeChargeDto;
import com.example.shoppingcart.stripePayment.dto.StripeSubscriptionDto;
import com.example.shoppingcart.stripePayment.dto.StripeSubscriptionResponse;
import com.example.shoppingcart.stripePayment.dto.StripeTokenDto;
import com.stripe.model.Subscription;

public interface StripeService {
  public StripeTokenDto createCardToken(StripeTokenDto model);

  public StripeChargeDto charge(StripeChargeDto chargeRequest);

  public StripeSubscriptionResponse createSubscription(
      StripeSubscriptionDto subscriptionDto);

  public Subscription cancelSubscription(String subscriptionId);
}
