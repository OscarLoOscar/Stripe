package com.example.shoppingcart.stripePayment.service.Impl;

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
public class StripeServiceImpl implements StripeService{
  @Value("${stripe.api.secretKey}")
  private String StripeApiKey;

  @PostConstruct
  public void init() {
    Stripe.apiKey = StripeApiKey;
  }

  @Override
  public StripeTokenDto createCardToken(StripeTokenDto model) {
    StripeTokenDto stripeTokenDto = new StripeTokenDto();
    try {
      Map<String, Object> cardParam = new HashMap<>();
      cardParam.put("card_number", model.getCardNumber());
      cardParam.put("card_exp_month", model.getExpMonth());
      cardParam.put("card_exp_year", model.getExpYear());
      cardParam.put("card_cvc", model.getCvc());
      Map<String, Object> tokenParam = new HashMap<>();
      tokenParam.put("card", cardParam);
      Token token = Token.create(tokenParam);
      if (token != null && token.getId() != null) {
        stripeTokenDto.setToken(token.getId());
        stripeTokenDto.setSuccess(true);
      }
      return model;

    } catch (Exception e) {
      stripeTokenDto.setSuccess(false);
    }
    return stripeTokenDto;
  }

  @Override
  public StripeChargeDto charge(StripeChargeDto chargeRequest) {
    try {
      chargeRequest.setSuccess(false);
      Map<String, Object> chargeParams = new HashMap<>();
      chargeParams.put("amount", chargeRequest.getAmount());
      chargeParams.put("currency",Currency.HKD.name());
      chargeParams.put("description", "Payment for id " + chargeRequest.getAdditionInfo().getOrDefault("ID_TAG", ""));
      chargeParams.put("source", chargeRequest.getStripeToken());

      Map<String,Object> metaData = new HashMap<>();
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
