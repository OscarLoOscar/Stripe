package com.example.shoppingcart.stripePayment.service.Impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.example.shoppingcart.infra.enums.Currency;
import com.example.shoppingcart.stripePayment.dto.StripeChargeDto;
import com.example.shoppingcart.stripePayment.dto.StripeTokenDto;
import com.example.shoppingcart.stripePayment.service.StripeService;
import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import jakarta.annotation.PostConstruct;

@Service
public class StripeServiceImpl implements StripeService {
  @Value("${stripe.api.publicKey}")
  private String StripePublicKey;

  @Value("${stripe.api.secretKey}")
  private String StripeSecretKey;

  @PostConstruct
  public void initPublicKey() {
    Stripe.apiKey = StripePublicKey;
  }

  @PostConstruct
  public void initSecretKey() {
    Stripe.apiKey = StripeSecretKey;
  }

  @Override
  public StripeTokenDto createCardToken(StripeTokenDto model) {
    try {
      Map<String, Object> card = new HashMap<>();
      card.put("number", model.getCardNumber());
      card.put("exp_month", Integer.parseInt(model.getExpMonth()));
      card.put("exp_year", Integer.parseInt(model.getExpYear()));
      card.put("cvc", model.getCvc());
      Map<String, Object> params = new HashMap<>();
      params.put("card", card);
      Token token = Token.create(params);
      if (token != null && token.getId() != null) {
        model.setSuccess(true);
        model.setToken(token.getId());
      }
      return model;
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  @Override
  public StripeChargeDto charge(StripeChargeDto chargeRequest) {
    try {
      chargeRequest.setSuccess(false);
      Map<String, Object> chargeParams = new HashMap<>();
      chargeParams.put("amount",
          Math.round(Double.parseDouble(chargeRequest.getAmount())* 100));
      chargeParams.put("currency", Currency.HKD.name());
      chargeParams.put("description", "Payment for id "
          + chargeRequest.getAdditionInfo().getOrDefault("ID_TAG", ""));
      chargeParams.put("source", chargeRequest.getStripeToken());

      Map<String, Object> metaData = new HashMap<>();
      metaData.put("id", chargeRequest.getChargeId());
      metaData.putAll(chargeRequest.getAdditionInfo());
      chargeParams.put("metadata", metaData);
      Charge charge = Charge.create(chargeParams);
      chargeRequest.setMessage(charge.getOutcome().getSellerMessage());

      if (charge.getPaid()) {
        chargeRequest.setStripeToken(charge.getId());
        chargeRequest.setSuccess(true);
      }
      return chargeRequest;
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }
}
