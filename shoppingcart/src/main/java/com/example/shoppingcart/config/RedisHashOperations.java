package com.example.shoppingcart.config;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import com.example.shoppingcart.model.CartItemData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RedisHashOperations {

  final String USER_ID = "USER_ID_";
  private final RedisTemplate<String, Object> redisTemplate;
  private final HashOperations<String, String, Object> hashOperations;
  private final ObjectMapper objectMapper;

  public RedisHashOperations(RedisTemplate<String, Object> redisTemplate,
      ObjectMapper objectMapper) {
    this.redisTemplate = redisTemplate;
    this.hashOperations = redisTemplate.opsForHash();
    this.objectMapper = objectMapper;
  }

  public boolean put(String key, String hashKey, Object value) {
    try {
      String serializedValue = objectMapper.writeValueAsString(value);
      hashOperations.put(USER_ID + key, hashKey, serializedValue);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public Object get(String key, String hashKey) {
    try {
      return redisTemplate.opsForHash().get(key, hashKey);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public List<CartItemData> getAll(String key) {
    try {

      BoundHashOperations<String, String, Object> boundHashOperations =
          redisTemplate.boundHashOps(USER_ID + key);
      List<Object> redisValues = boundHashOperations.values();
      // List<Object> redisValues = hashOperations.values(USER_ID + key);
      return redisValues.stream()//
          .map(value -> deserializeCartItemData(value))//
          .filter(Objects::nonNull)//
          .collect(Collectors.toList());
    } catch (Exception e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }

  private CartItemData deserializeCartItemData(Object value) {
    try {
      return objectMapper.readValue((String) value, CartItemData.class);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return null;
    }
  }


  public boolean delete(String key, String hashKey) {
    try {
      Long deletedCount = redisTemplate.opsForHash().delete(key, hashKey);
      return deletedCount > 0;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public boolean deleteAll(String userID) {
    try {
      Set<String> keys = redisTemplate.keys(USER_ID + userID + "*");
      redisTemplate.delete(keys);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }


  public boolean expire(String key, long time) {
    try {
      if (time > 0) {
        redisTemplate.expire(USER_ID + key, time, TimeUnit.MILLISECONDS);
        return true;
      }
      return false;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }



  public Map<Object, Object> entries(String key) {
    try {
      return redisTemplate.opsForHash().entries(key);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }


  public boolean hasKey(String key, String hashKey) {
    try {
      return redisTemplate.opsForHash().hasKey(key, hashKey);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public static String formatKey(String head, String source) {
    return new StringBuilder(head).append(":").append(source).toString();
  }
}
