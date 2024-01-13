package com.example.shoppingcart.stripePayment.controller;

import static java.util.Objects.nonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.shoppingcart.stripePayment.controller.Impl.StripeApiImpl;
import com.example.shoppingcart.stripePayment.dto.StripeChargeDto;
import com.example.shoppingcart.stripePayment.dto.StripeSubscriptionDto;
import com.example.shoppingcart.stripePayment.dto.StripeSubscriptionResponse;
import com.example.shoppingcart.stripePayment.dto.StripeTokenDto;
import com.example.shoppingcart.stripePayment.dto.SubscriptionCancelRecord;
import com.example.shoppingcart.stripePayment.service.StripeService;
import com.stripe.model.Subscription;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/public/stripe")
public class StripeApi implements StripeApiImpl {

  @Autowired
  private StripeService stripeService;

  @Override
  public StripeTokenDto createCardToken(StripeTokenDto model,
      JwtAuthenticationToken jwt) {
    return stripeService.createCardToken(model);
  }

  @Override
  public StripeChargeDto charge(StripeChargeDto model,
      JwtAuthenticationToken jwt) {
    return stripeService.charge(model);
  }

  @Override
  public StripeSubscriptionResponse subscription(StripeSubscriptionDto model,
      JwtAuthenticationToken jwt) {
    return stripeService.createSubscription(model);
  }

  @Override
  public SubscriptionCancelRecord cancelSubscription(String id,
      JwtAuthenticationToken jwt) {
    log.info("id " + id);
    Subscription subscription = stripeService.cancelSubscription(id);
    log.info("subscription " + subscription);
    if (nonNull(subscription)) {
      log.info("API run this line");
      return new SubscriptionCancelRecord(subscription.getStatus());
    }
    log.info("API run null ");
    return null;
  }
}
