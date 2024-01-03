package com.example.shoppingcart.model;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Builder
@JsonPropertyOrder({"tpid", "tid", "product", "quantity", "subtotal"})
public class TransactionProductData {

  private Long tpid;

  private Long tid;

  @JsonProperty("product")
  private CartItemData cartItemData;

  private BigDecimal quantity;

  @JsonProperty("subtotal")
  private BigDecimal totalPrice;

}
