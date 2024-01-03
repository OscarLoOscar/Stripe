package com.example.shoppingcart.services.impl;

import com.example.shoppingcart.model.ChargeRequest;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

public interface StripeServiceImpl {
  Charge createPaymentSession(Long userId,ChargeRequest chargeRequest)  throws StripeException ;
}
