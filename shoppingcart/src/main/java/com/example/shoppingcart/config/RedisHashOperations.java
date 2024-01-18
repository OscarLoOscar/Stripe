package com.example.shoppingcart.config;

import java.util.Map;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import com.example.shoppingcart.exception.setting.ApiResp;
import com.example.shoppingcart.exception.setting.Code;

@Configuration
public class RedisHashOperations {


  private final RedisTemplate<String, Object> redisTemplate;
  private final HashOperations<String, String, Object> hashOperations;

  public RedisHashOperations(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
    this.hashOperations = redisTemplate.opsForHash();
  }

  public boolean put(String key, String hashKey, Object value) {
    try {
      redisTemplate.opsForHash().put(key, hashKey, value);
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

  public boolean delete(String key, String hashKey) {
    try {
      Long deletedCount = redisTemplate.opsForHash().delete(key, hashKey);
      return deletedCount > 0;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public boolean expire(String key, long time) {
    try {
      if (time > 0) {
        redisTemplate.expire(key, time,
            java.util.concurrent.TimeUnit.MILLISECONDS);
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
    return head.concat(":").concat(source);
  }
}
