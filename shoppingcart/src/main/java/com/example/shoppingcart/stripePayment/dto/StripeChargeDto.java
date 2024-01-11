package com.example.shoppingcart.stripePayment.dto;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;

@Data
public class StripeChargeDto {
  private String stripeToken;
  private String userName;
  private String amount;
  private boolean success;
  private String message;
  private String chargeId;
  private Map<String, Object> additionInfo = new HashMap<>();
}
