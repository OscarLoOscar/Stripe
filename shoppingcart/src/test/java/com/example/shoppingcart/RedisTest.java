// package com.example.shoppingcart;

// import java.io.Serializable;
// import java.time.LocalDateTime;
// import java.util.List;
// import java.util.Set;
// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.redis.core.HashOperations;
// import org.springframework.data.redis.core.ListOperations;
// import org.springframework.data.redis.core.RedisTemplate;
// import org.springframework.data.redis.core.HashOperations;
// import org.springframework.data.redis.core.ValueOperations;
// import org.springframework.data.redis.core.ZSetOperations;
// import com.example.shoppingcart.Redis.dto.RedisTestDTO;
// import com.example.shoppingcart.Redis.infra.SKUStatus;
// import com.example.shoppingcart.Redis.model.ShopCartConstant;
// import com.example.shoppingcart.Redis.model.Sku;
// import com.example.shoppingcart.config.RedisHelper;

// public class RedisTest extends ShoppingcartApplicationTests {
//  @Autowired
//     private RedisTemplate<String, Serializable> redisTemplate;
//   private Sku sku;

//   private RedisTestDTO redisTestDTO;

//   @BeforeAll
//   public void before() {
//     sku = new Sku()//
//         .setGoodsId(111111L)//
//         .setShopId(123457L)//
//         .setShopName("MiCai Shop")//
//         .setGoodsName("帆布鞋")//
//         .setPrice(15000L)//
//         .setStock(100).setPicture("http://...pic...123.png")//
//         .setProperties("白色;40").setMax(300)//
//         .setStatus(SKUStatus.NORMAL.getCode())//
//         .setCreateTime(LocalDateTime.now())//
//         .setUpDateTime(LocalDateTime.now());

//     redisTestDTO = new RedisTestDTO().setSkuId(Long.MAX_VALUE)
//         .setCount(ShopCartConstant.MAX);
//   }

//   @Test
//   public void zSetTest() {
//     String key = "ZSET_TEST";
//     ZSetOperations<String, Serializable> zSetOperations =
//     redisTemplate.opsForZSet();
//     zSetOperations.add(key, redisTestDTO, 1); // skiplist
//     Set<Serializable> range = zSetOperations.range(key, 0, -1);
//     assert range != null;
//     range.forEach(System.out::println);
//   }

//   @Test
//   public void hashTest() {
//     HashOperations<String, Object, Object> hashOperations =
//     redisTemplate.opsForHash();
//     hashOperations.put("Cart", "用户id", sku);
//     Sku result = (Sku) hashOperations.get("Cart", "用户id");
//     System.out.println(result);
//   }

//   @Test
//   public void stringTest() {
//     ValueOperations<String, Serializable> valueOperations =
//     redisTemplate.opsForValue();
//     valueOperations.set("SKU", sku);
//     Serializable sku = valueOperations.get("SKU");
//     System.out.println(sku);
//   }

//   @Test
//   public void listTest() {
//     String key = "LIST_TEST";
//     ListOperations<String, Serializable> listOperations =
//     redisTemplate.opsForList();
//     listOperations.leftPush(key, redisTestDTO);
//     List<Serializable> range = listOperations.range(key, 0, -1);
//     assert range != null;
//     range.forEach(System.out::println);
//   }

// }
