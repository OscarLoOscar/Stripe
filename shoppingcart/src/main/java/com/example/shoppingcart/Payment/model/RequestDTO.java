package com.example.shoppingcart.payment.model;

import java.util.List;
import com.stripe.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestDTO {
  List<Product> items;
  String customerName;
  String customerEmail;
  String subscriptionId;
  boolean invoiceNeeded;

}
