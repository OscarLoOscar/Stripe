package com.example.shoppingcart.stripePayment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.shoppingcart.stripePayment.dto.StripeChargeDto;
import com.example.shoppingcart.stripePayment.dto.StripeTokenDto;
import com.example.shoppingcart.stripePayment.service.StripeService;

@RestController
@RequestMapping("/public/stripe")
public class StripeApi implements StripeApiImpl {

  @Autowired
  private StripeService stripeService;

  @Override
  public StripeTokenDto createCardToken(StripeTokenDto model,JwtAuthenticationToken jwt) {
    return stripeService.createCardToken(model);
  }

  @Override
  public StripeChargeDto charge(StripeChargeDto model,JwtAuthenticationToken jwt) {
    return stripeService.charge(model);
  }
}
