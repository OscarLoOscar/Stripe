package com.example.shoppingcart.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonPropertyOrder({"pid", "name", "description", "image_url", "price",
    "unitStock", "has_stock"})
public class ProductData {

  @JsonProperty(value = "pid")
  Long pid;

  @JsonProperty(value = "name")
  String productName;

  @JsonProperty(value = "description")
  String productDescription;

  @JsonProperty(value = "image_url")
  String imageUrl;

  @JsonProperty(value = "price")
  Double productPrice;

  @JsonProperty(access = Access.READ_ONLY)
  private Integer unitStock;

  @JsonProperty(value = "has_stock")
  boolean hasStock() {
    if (this.getUnitStock() > 0)
      return true;
    return false;
  }
}
