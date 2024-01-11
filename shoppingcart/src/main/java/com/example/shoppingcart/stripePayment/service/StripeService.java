package com.example.shoppingcart.stripePayment.service;

import com.example.shoppingcart.stripePayment.dto.StripeChargeDto;
import com.example.shoppingcart.stripePayment.dto.StripeTokenDto;

public interface StripeService {
  public StripeTokenDto createCardToken(StripeTokenDto model);

  public StripeChargeDto charge(StripeChargeDto chargeRequest);
}
