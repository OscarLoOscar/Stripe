package com.example.shoppingcart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class AppConfig {

  @Bean
  public RedisTemplate<String, Object> redisTemplate(
      RedisConnectionFactory factory, ObjectMapper redisObjectMapper) {
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(factory);
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setValueSerializer(
        new GenericJackson2JsonRedisSerializer(redisObjectMapper));
    redisTemplate.afterPropertiesSet();
    return redisTemplate;
  }

  @Bean
  RedisHelper redisProfileHelper(RedisConnectionFactory factory, //
      ObjectMapper redisObjectMapper) {
    return new RedisHelper(factory, redisObjectMapper);
  }

  @Bean
  RestTemplate restTemplate() {// method name -> bean name
    return new RestTemplate();
  }

  // @Bean
  // public RedisCacheConfiguration redisCacheConfiguration() {
  // return RedisCacheConfiguration.defaultCacheConfig()//
  // // 自定义 key 的序列化器
  // .serializeKeysWith(RedisSerializationContext.SerializationPair
  // .fromSerializer(keySerializer()))
  // // 自定义 value 的序列化器
  // .serializeValuesWith(RedisSerializationContext.SerializationPair
  // .fromSerializer(genericJackson2JsonRedisSerializer()))
  // // 禁止缓存 null 值
  // .disableCachingNullValues();
  // }

  // @Bean
  // public RedisSerializer<String> keySerializer() {
  // return new StringRedisSerializer();
  // }

  // @Bean
  // public GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer() {
  // return new GenericJackson2JsonRedisSerializer();
  // }
}
