package com.example.shoppingcart.stripePayment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StripeTokenDto {
  // @JsonProperty("card_number")
  private String cardNumber;
  // @JsonProperty("card_exp_month")
  private String expMonth;
  // @JsonProperty("card_exp_year")
  private String expYear;
  // @JsonProperty("card_cvc")
  private String cvc;
  private String token;
  private String username;
  private boolean success;
}
