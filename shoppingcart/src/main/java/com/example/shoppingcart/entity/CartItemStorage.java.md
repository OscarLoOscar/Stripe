package com.example.shoppingcart.entity;

import java.util.HashMap;
import java.util.Map;
// import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
// @Document(collection = "cartItems")
public class CartItemStorage {
  private static final Map<Long, Integer> cartItemMap = new HashMap<>();

  public void addCartItem(Long productId, int quantity) {
    cartItemMap.put(productId,
        cartItemMap.getOrDefault(productId, 0) + quantity);
  }

  public void changeCartItemQuantity(Long productId, int quantity) {
    cartItemMap.put(productId, quantity);
  }

  public Map<Long, Integer> getCartItems() {
    return new HashMap<>(cartItemMap);
  }

  public void clearCartItems() {
    cartItemMap.clear();
  }
}
