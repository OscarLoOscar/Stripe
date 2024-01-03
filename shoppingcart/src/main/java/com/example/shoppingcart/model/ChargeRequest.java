package com.example.shoppingcart.model;

import com.example.shoppingcart.entity.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ChargeRequest {
  private String description;
  private int amount;
  private Currency currency;
  private String token;
}
