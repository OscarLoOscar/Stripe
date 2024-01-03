package com.example.shoppingcart.model;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Builder
@JsonPropertyOrder({"pid", "name", "description", "image_url", "price",
    "cart_quantity", "stock"})
public class CartItemData {

  //private Long cid;

  private Long pid;

  private String name;

  @JsonProperty(value = "image_url")
  private String imageUrl;

  private String description;

  private BigDecimal price;

  @JsonProperty(value = "cart_quantity")
  private BigDecimal quantity;

  private Integer stock;

}
