package com.example.shoppingcart.Redis.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.shoppingcart.Redis.service.ShopCartService;
import com.example.shoppingcart.config.RedisUtils;
import com.example.shoppingcart.model.CartItemData;
import com.example.shoppingcart.services.ProductService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ShopCartServiceImpl implements ShopCartService {


  // private final String PRODUCT_KEY = "product_number_";

  @Autowired
  private RedisUtils redisUtils;

  @Autowired
  private ProductService productService;

  @Override
  public boolean addCartItem(long userId, long pid, Integer quantity) {
    return redisUtils.hset(String.valueOf(userId), String.valueOf(pid),
        quantity);
  }

  @Override
  public List<CartItemData> getAll(String key) {
    List<CartItemData> cartItem = new ArrayList<>();
    Map<Object, Object> map = redisUtils.hmget(key);
    for (Map.Entry<Object, Object> entry : map.entrySet()) {
      CartItemData cartItemData = new CartItemData();
      cartItemData.setPid(Long.valueOf(entry.getKey().toString()));
      cartItemData.setQuantity(
          BigDecimal.valueOf(Double.valueOf(entry.getValue().toString())));
      productService.getAllProduct().stream()
          .filter(p -> p.getPid() == cartItemData.getPid()).findFirst()
          .ifPresent(p -> {
            cartItemData.setPrice(BigDecimal.valueOf(p.getProductPrice()));
            cartItemData.setName(p.getProductName());
            cartItemData.setImageUrl(p.getImageUrl());
            cartItemData.setQuantity(BigDecimal.valueOf(Double.valueOf(entry.getValue().toString())));
            cartItemData.setStock(p.getUnitStock());
            cartItemData.setDescription(p.getProductDescription());
          });
      cartItem.add(cartItemData);
    }
    return cartItem;
  }

  @Override
  public boolean updateCartQuantity(long userId, long pid, int quantity) {
    return redisUtils.hset(String.valueOf(userId), String.valueOf(pid),
        quantity);
  }

  @Override
  public boolean deleteAllCartItem(long userId) {
    redisUtils.del(String.valueOf(userId));
    return true;
  }

  // @Override
  // public Optional<List<CartItemData>> findAllByUserUid(Long uid) {
  // List<CartItemData> cartItems =
  // redisHashOperations.getAll(String.valueOf(uid));
  // return Optional.ofNullable(cartItems);
  // }

  // @Override
  // public Optional<List<CartItemData>> getUserCartItemsByProductId(Long pid) {
  // List<CartItemData> cartItems =
  // redisHashOperations.getAll(formatProductKey(pid));
  // return Optional.ofNullable(cartItems);
  // }

  // @Override
  // public List<CartItemData> getAll(String key) {
  // return redisHashOperations.getAll(key);
  // }

  // @Override
  // public boolean addCartItem(long userId, long pid, Integer quantity)
  // throws UserNotExistException, ProductNotExistException {
  // return redisHashOperations.put(String.valueOf(userId),
  // formatProductKey(pid), quantity);
  // }

  // @Override
  // public boolean updateCartQuantity(long userId, long pid, int quantity)
  // throws ProductNotExistException, UserNotExistException {
  // // Assuming pid is used as the hashKey
  // if (redisHashOperations.hasKey(String.valueOf(userId),
  // formatProductKey(pid))) {
  // redisHashOperations.put(String.valueOf(userId), formatProductKey(pid),
  // quantity);
  // return true;
  // }
  // return false;
  // }

  // @Override
  // public CartItemData getCartItemDetails(long userId, long productId) {
  // // Assuming productId is used as the hashKey
  // Object cartItem = redisHashOperations.get(String.valueOf(userId),
  // formatProductKey(productId));
  // if (cartItem instanceof CartItemData) {
  // return (CartItemData) cartItem;
  // }
  // return null;
  // }

  // @Override
  // public void deleteCartItemByCartItemId(long userId, long cartItemId)
  // throws UserNotExistException, CartItemNotFoundException {
  // // Assuming cartItemId is used as the hashKey
  // redisHashOperations.delete(String.valueOf(userId),
  // String.valueOf(cartItemId));
  // }

  // @Override
  // public void deleteAllCartItem(String userId) {
  // redisHashOperations.deleteAll(userId);
  // }

  // @Override
  // public boolean checkStock(Product productEntity, Integer quantity) {
  // return quantity <= productEntity.getUnitStock()
  // && productEntity.getUnitStock() != 0;
  // }

  // private String formatProductKey(long productId) {
  // return PRODUCT_KEY + productId;
  // }

}
