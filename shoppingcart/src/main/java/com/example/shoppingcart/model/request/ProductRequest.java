package com.example.shoppingcart.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
  String productName;

  String productDescription;  

  String imageUrl;

  Double productPrice;

  Integer unitStock;
}
