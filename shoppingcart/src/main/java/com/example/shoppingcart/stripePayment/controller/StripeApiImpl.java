package com.example.shoppingcart.stripePayment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.example.shoppingcart.stripePayment.dto.StripeChargeDto;
import com.example.shoppingcart.stripePayment.dto.StripeSubscriptionDto;
import com.example.shoppingcart.stripePayment.dto.StripeSubscriptionResponse;
import com.example.shoppingcart.stripePayment.dto.StripeTokenDto;
import com.example.shoppingcart.stripePayment.dto.SubscriptionCancelRecord;

public interface StripeApiImpl {

  @PostMapping("/card/token")
  @ResponseStatus(HttpStatus.CREATED)
  public StripeTokenDto createCardToken(@RequestBody StripeTokenDto model,
      JwtAuthenticationToken jwt);

  @PostMapping("/charge")
  @ResponseStatus(HttpStatus.OK)
  public StripeChargeDto charge(@RequestBody StripeChargeDto model,
      JwtAuthenticationToken jwt);

  @PostMapping("/customer/subscription")
  @ResponseStatus(HttpStatus.CREATED)
  public StripeSubscriptionResponse subscription(
      @RequestBody StripeSubscriptionDto model, JwtAuthenticationToken jwt);

  @DeleteMapping("/subscription/{id}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public SubscriptionCancelRecord cancelSubscription(@PathVariable String id);

}
