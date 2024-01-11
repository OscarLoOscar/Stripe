package com.example.shoppingcart.stripePayment.controller;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.shoppingcart.stripePayment.dto.StripeChargeDto;
import com.example.shoppingcart.stripePayment.dto.StripeTokenDto;

public interface StripeApiImpl {

  @PostMapping("/card/token")
  public StripeTokenDto createCardToken(@RequestBody StripeTokenDto model , JwtAuthenticationToken jwt);

  @PostMapping("/charge")
  public StripeChargeDto charge(@RequestBody StripeChargeDto chargeRequest,JwtAuthenticationToken jwt);
}
