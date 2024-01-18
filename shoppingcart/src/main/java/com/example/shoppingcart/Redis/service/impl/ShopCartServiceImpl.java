package com.example.shoppingcart.Redis.service.impl;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.shoppingcart.Redis.model.OperateVo;
import com.example.shoppingcart.Redis.service.ShopCartService;
import com.example.shoppingcart.config.RedisHashOperations;
import com.example.shoppingcart.exception.setting.ApiResp;
import com.example.shoppingcart.exception.setting.Code;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ShopCartServiceImpl implements ShopCartService {


  private final String CART_KEY = "cart:";

  @Autowired
  private RedisHashOperations redisHashOperations;

  @Override
  public ApiResp<Boolean> addCart(OperateVo vo) {
    String key = CART_KEY + vo.getUserId();
    String hashKey = vo.getSkuId();
    // Check if the item is already in the cart
    Object existingItemResponse = redisHashOperations.get(key, hashKey);
    if (existingItemResponse != null) {
      // Item exists, update quantity
      int currentQuantity = (int) existingItemResponse;
      redisHashOperations.put(key, hashKey, currentQuantity + vo.getNum());
    } else {
      // Item doesn't exist, add to cart
      redisHashOperations.put(key, hashKey, vo.getNum());
    }
    return ApiResp.<Boolean>builder().ok().build();
  }

  @Override
  public ApiResp<Map<Object, Object>> showCart(OperateVo vo) {
    String key = CART_KEY + vo.getUserId();
    Map<Object, Object> cartResponse = redisHashOperations.entries(key);
    if (cartResponse.get(key) != null) {
      // Process and return the cart data as needed
      return ApiResp.<Map<Object, Object>>builder().ok().data(cartResponse)
          .build();
    } else {
      return ApiResp.<Map<Object, Object>>builder()
          .error(Code.REDIS_GET_ITEM_FROM_CART_FAIL).build();
    }
  }

  @Override
  public ApiResp<Boolean> updateCartNum(OperateVo vo) {
    String key = CART_KEY + vo.getUserId();
    String hashKey = vo.getSkuId();
    // Check if the item is in the cart
    boolean itemExistsResponse = redisHashOperations.hasKey(key, hashKey);
    if (itemExistsResponse) {
      // Update the quantity
      redisHashOperations.put(key, hashKey, vo.getNum());
      return ApiResp.<Boolean>builder().ok().build();
    } else {
      return ApiResp.<Boolean>builder().error(Code.PRODUCT_NOT_EXIST).build();
    }
  }

  @Override
  public ApiResp<Boolean> delCart(OperateVo vo) {
    String key = CART_KEY + vo.getUserId();
    String hashKey = vo.getSkuId();
    // Remove the item from the cart
    redisHashOperations.delete(key, hashKey);
    return ApiResp.<Boolean>builder().ok().build();
  }

  @Override
  public ApiResp<Boolean> checkNumCart(OperateVo vo) {
    String key = CART_KEY + vo.getUserId();
    Map<Object, Object> cartResponse = redisHashOperations.entries(key);
    if (cartResponse != null && !cartResponse.isEmpty()) {
      // Process and return the cart data as needed
      return ApiResp.<Boolean>builder().ok().data(processCartData(cartResponse))
          .build();
    } else {
      return ApiResp.<Boolean>builder()
          .error(Code.REDIS_GET_ITEM_FROM_CART_FAIL).build();
    }
  }

  private Boolean processCartData(Map<Object, Object> cartMap) {
    for (Map.Entry<Object, Object> entry : cartMap.entrySet()) {
      String skuId = (String) entry.getKey();
      Integer num = (Integer) entry.getValue();
      // Check if the item is in the cart
      boolean itemExistsResponse = redisHashOperations.hasKey(CART_KEY, skuId);
      if (itemExistsResponse) {
        // Update the quantity
        redisHashOperations.put(CART_KEY, skuId, num);
      } else {
        return false;
      }
    }
    return true;
  }
}
