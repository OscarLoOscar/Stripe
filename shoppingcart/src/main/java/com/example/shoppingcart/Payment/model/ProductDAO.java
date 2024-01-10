package com.example.shoppingcart.payment.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import com.stripe.model.Price;
import com.stripe.model.Product;
import lombok.Getter;

@Getter
public class ProductDAO {
  static List<Product> products;
  static {
    products = new ArrayList<>();
    Product sampleProduct = new Product();
    Price samplePrice = new Price();
    sampleProduct.setName("Puma Shoes");
    sampleProduct.setId("shoe");
    samplePrice.setCurrency("usd");
    samplePrice.setUnitAmountDecimal(BigDecimal.valueOf(2000));
    sampleProduct.setDefaultPriceObject(samplePrice);
    products.add(sampleProduct);

    sampleProduct = new Product();
    samplePrice = new Price();
    sampleProduct.setName("Nike Sliders");
    sampleProduct.setId("slippers");
    samplePrice.setCurrency("usd");
    samplePrice.setUnitAmountDecimal(BigDecimal.valueOf(1000));
    sampleProduct.setDefaultPriceObject(samplePrice);
    products.add(sampleProduct);

    sampleProduct = new Product();
    samplePrice = new Price();

    sampleProduct.setName("Apple Music+");
    sampleProduct.setId("music");
    samplePrice.setCurrency("usd");
    samplePrice.setUnitAmountDecimal(BigDecimal.valueOf(499));
    sampleProduct.setDefaultPriceObject(samplePrice);
    products.add(sampleProduct);
  }

  public static Product getProduct(String id) {
    if ("shoe".equals(id)) {
      return products.get(0);
    } else if ("slippers".equals(id)) {
      return products.get(1);
    } else if ("music".equals(id)) {
      return products.get(2);
    } else
      return new Product();
  }
}
