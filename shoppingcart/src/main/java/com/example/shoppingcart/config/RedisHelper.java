package com.example.shoppingcart.config;

import java.util.concurrent.TimeUnit;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.web.client.ResourceAccessException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import io.swagger.v3.oas.annotations.Operation;

public class RedisHelper {

  // key value pair, key must be unqiue, most likely String
  private RedisTemplate<String, Object> redisTemplate;

  // This factory is used to create a connection to the Redis server.
  public RedisHelper(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  public RedisHelper(RedisConnectionFactory factory) {
    ObjectMapper objectMapper = new ObjectMapper() //
        .registerModule(new ParameterNamesModule())
        .registerModule(new Jdk8Module()) //
        .registerModule(new JavaTimeModule());
    this.redisTemplate = template(factory, objectMapper);
  }

  // takes a RedisConnectionFactory and an ObjectMapper as arguments.
  // The ObjectMapper is used to serialize and deserialize objects to and from JSON.
  public RedisHelper(RedisConnectionFactory factory,
      ObjectMapper redisObjectMapper) {
    this.redisTemplate = template(factory, redisObjectMapper);
  }

  @Operation(
      summary = "The RedisConnectionFactory is used to create a connection to the Redis server."//
          + "The ObjectMapper is used to serialize and deserialize objects to and from JSON."//
          + "create a RedisTemplate object that is customized for your specific needs.")
  public static RedisTemplate<String, Object> template(
      RedisConnectionFactory factory, ObjectMapper redisObjectMapper) { // method name -> bean name
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(factory);
    redisTemplate.setKeySerializer(RedisSerializer.string());
    redisTemplate.setValueSerializer(RedisSerializer.json());
    // redisTemplate.setHashKeySerializer(RedisSerializer.string());
    // redisTemplate.setHashValueSerializer(RedisSerializer.json());
    redisTemplate.afterPropertiesSet();
    Jackson2JsonRedisSerializer<Object> serializer =
        new Jackson2JsonRedisSerializer<>(redisObjectMapper, Object.class);
    redisTemplate.setValueSerializer(serializer);
    return redisTemplate;
  }

  public static RedisTemplate<String, Object> template(
      RedisConnectionFactory factory) {
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(factory);
    redisTemplate.setKeySerializer(RedisSerializer.string());
    redisTemplate
        .setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
    redisTemplate.afterPropertiesSet();
    return redisTemplate;
  }

  // Sets a key-value pair in Redis.
  public boolean set(String key, Object value) {
    try {
      redisTemplate.opsForValue().set(key, value);
      return true;
    } catch (Exception e) {
      throw new ResourceAccessException("Redis unavailable");
    }
  }

  // Sets a key-value pair in Redis.
  public boolean set(String key, Object value, long time) {
    try {
      System.out.println("set before = " + key + " " + value + " " + time);
      redisTemplate.opsForValue().set(key, value, time, TimeUnit.MILLISECONDS);
      System.out.println("set after");
      return true;
    } catch (Exception e) {
      System.out.println("redis.set = " + e.getMessage());
      throw new ResourceAccessException(
          "Redis unavailable (set method) msg = " + e.getMessage());
    }
  }

  // Gets the value associated with a key in Redis.
  public Object get(String key) {
    try {
      if (key != null) {
        System.out.println("!!!!!!!!!!!!!!!");
        Object object = redisTemplate.opsForValue().get(key);
        System.out.println("!!!!!!!!!!!!!" + object);
        return object;
      }
      return null;
    } catch (Exception e) {
      throw new ResourceAccessException(
          "Redis unavailable (set method) msg = " + e.getMessage());
    }
  }

  // Sets an expiration time for a key in Redis.
  public boolean expire(String key, long time) {
    try {
      if (time > 0) {
        redisTemplate.expire(key, time, TimeUnit.MILLISECONDS);
        return true;
      }
      return false;
    } catch (Exception e) {
      throw new ResourceAccessException("Redis unavailable.");
    }
  }
  public boolean delete (String key) {
    try {
      redisTemplate.delete(key);
      return true;
    } catch (Exception e) {
      throw new ResourceAccessException("Redis unavailable.");
    }
  }

  public static String formatKey(String head, String source) {
    return head.concat(":").concat(source);
  }
}
