package com.example.shoppingcart.Redis.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.shoppingcart.Redis.service.ShopCartService;
import com.example.shoppingcart.config.RedisHashOperations;
import com.example.shoppingcart.entity.Product;
import com.example.shoppingcart.exception.CartItemNotFoundException;
import com.example.shoppingcart.exception.ProductNotExistException;
import com.example.shoppingcart.exception.UserNotExistException;
import com.example.shoppingcart.model.CartItemData;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ShopCartServiceImpl implements ShopCartService {


  private final String PRODUCT_KEY = "product_number_";

  @Autowired
  private RedisHashOperations redisHashOperations;

  @Override
  public Optional<List<CartItemData>> findAllByUserUid(Long uid) {
    List<CartItemData> cartItems =
        redisHashOperations.getAll(String.valueOf(uid));
    return Optional.ofNullable(cartItems);
  }

  @Override
  public Optional<List<CartItemData>> getUserCartItemsByProductId(Long pid) {
    List<CartItemData> cartItems =
        redisHashOperations.getAll(formatProductKey(pid));
    return Optional.ofNullable(cartItems);
  }

  @Override
  public List<CartItemData> getAll(String key) {
    return redisHashOperations.getAll(key);
  }

  @Override
  public boolean addCartItem(long userId, long pid, Integer quantity)
      throws UserNotExistException, ProductNotExistException {
    return redisHashOperations.put(String.valueOf(userId),
        formatProductKey(pid), quantity);
  }

  @Override
  public boolean updateCartQuantity(long userId, long pid, int quantity)
      throws ProductNotExistException, UserNotExistException {
    // Assuming pid is used as the hashKey
    if (redisHashOperations.hasKey(String.valueOf(userId),
        formatProductKey(pid))) {
      redisHashOperations.put(String.valueOf(userId), formatProductKey(pid),
          quantity);
      return true;
    }
    return false;
  }

  @Override
  public CartItemData getCartItemDetails(long userId, long productId) {
    // Assuming productId is used as the hashKey
    Object cartItem = redisHashOperations.get(String.valueOf(userId),
        formatProductKey(productId));
    if (cartItem instanceof CartItemData) {
      return (CartItemData) cartItem;
    }
    return null;
  }

  @Override
  public void deleteCartItemByCartItemId(long userId, long cartItemId)
      throws UserNotExistException, CartItemNotFoundException {
    // Assuming cartItemId is used as the hashKey
    redisHashOperations.delete(String.valueOf(userId),
        String.valueOf(cartItemId));
  }

  @Override
  public void deleteAllCartItem(String userId) {
    redisHashOperations.deleteAll(userId);
  }

  @Override
  public boolean checkStock(Product productEntity, Integer quantity) {
    return quantity <= productEntity.getUnitStock()
        && productEntity.getUnitStock() != 0;
  }

  private String formatProductKey(long productId) {
    return PRODUCT_KEY + productId;
  }

}
