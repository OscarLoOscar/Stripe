package com.example.shoppingcart.services.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.shoppingcart.Redis.service.ShopCartService;
import com.example.shoppingcart.config.RedisHashOperations;
import com.example.shoppingcart.config.RedisHelper;
import com.example.shoppingcart.entity.CartItem;
import com.example.shoppingcart.entity.Product;
import com.example.shoppingcart.entity.UserEntity;
import com.example.shoppingcart.exception.CartItemNotFoundException;
import com.example.shoppingcart.exception.ProductNotExistException;
import com.example.shoppingcart.exception.UserNotExistException;
import com.example.shoppingcart.exception.setting.Code;
import com.example.shoppingcart.model.CartItemData;
import com.example.shoppingcart.model.Mapper;
import com.example.shoppingcart.model.ProductData;
import com.example.shoppingcart.repository.CartItemRepository;
import com.example.shoppingcart.services.CartItemService;
import com.example.shoppingcart.services.ProductService;
import com.example.shoppingcart.services.TransactionProductService;
import com.example.shoppingcart.services.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CartItemServiceImpl implements CartItemService {

  @PersistenceContext
  private EntityManager entityManager;

  @Autowired
  CartItemRepository cartItemRepository;

  @Autowired
  UserService userService;

  @Autowired
  ProductService productService;

  @Autowired
  TransactionProductService transactionProductService;

  @Autowired
  ShopCartService shopCartService;

  @Autowired
  RedisHashOperations redisHashOperations;

  @Override
  @Transactional
  public void deleteCartItemByCartItemId(long userId, long cartItemId)
      throws UserNotExistException, CartItemNotFoundException {
    UserEntity userData = userService.getUserById(userId);
    Optional<CartItem> cartItemOptional =
        cartItemRepository.findById(cartItemId);

    if (cartItemOptional.isEmpty()) {
      throw new CartItemNotFoundException(Code.PRODUCT_NOT_EXIST);
    }

    CartItem cartItem = cartItemOptional.get();

    // Check if the cart item belongs to the specified user
    if (cartItem.getUser().getUserId() == userId) {
      cartItemRepository.delete(cartItem);
    } else {
      // Handle the case where the cart item doesn't belong to the user
      throw new UserNotExistException(Code.USER_NOT_FOUND);
    }
  }


  @Override
  @Transactional
  public boolean updateCartQuantity(long userId, long pid, int quantity)
      throws ProductNotExistException, UserNotExistException {
    UserEntity userData = userService.getUserById(userId);

    Optional<CartItem> optionalCartItem = getEntityByUidAndPid(userId, pid);

    if (optionalCartItem.isPresent()) {
      CartItem cartItem2 = optionalCartItem.get();
      cartItem2.setQuantity(BigDecimal.valueOf(quantity));
      log.info("Service quantity : " + quantity);
      cartItem2 = entityManager.merge(cartItem2);
      cartItemRepository.save(cartItem2);
    } else {
      // ProductData productEntity = productService.getProductById(pid);
      Product product = productService.getProductEntityById(pid);
      CartItem cartItementity = CartItem.builder()//
          .product(product)//
          .user(userData)//
          .quantity(BigDecimal.valueOf(quantity))//
          .build();
      cartItementity = entityManager.merge(cartItementity);
      cartItemRepository.save(cartItementity);
    }
    return true;
  }

  @Override
  public void deleteAllCartItem() {
    cartItemRepository.deleteAll();
  }

  @Override
  public Optional<List<CartItemData>> findAllByUserUid(Long uid) {
    List<CartItem> cartItems = cartItemRepository.findAllByUserUid(uid).get();
    if (cartItems.isEmpty()) {
      return Optional.empty();
    } else {
      return Optional.of(cartItems.stream()//
          .map(Mapper::map)//
          .collect(Collectors.toList()));
    }
  }


  @Override
  public Optional<List<CartItemData>> getUserCartItemsByProductId(Long pid) {
    List<CartItem> cartItemDatas =
        cartItemRepository.findAllByUserUid(pid).get();
    if (cartItemDatas.isEmpty()) {
      return Optional.empty();
    } else {
      return Optional.of(cartItemDatas.stream()//
          .map(Mapper::map)//
          .collect(Collectors.toList()));
    }
  }

  public ProductData getProductById(Long productId)
      throws ProductNotExistException {
    return productService.getProductById(productId);
  }

  public Optional<CartItem> getEntityByUidAndPid(Long uid, Long pid) {
    return cartItemRepository.findByUser_UidAndProduct_Pid(uid, pid);
  }

  public boolean isEnoughStock(Long pid, Integer quantity) {
    return productService.isEnoughStock(pid, quantity);
  }

  @Override
  public CartItemData getCartItemDetails(long uid, long pid) {
    Optional<CartItem> optionalCartItem =
        cartItemRepository.findByUser_UidAndProduct_Pid(uid, pid);

    if (optionalCartItem.isPresent()) {
      return Mapper.map(optionalCartItem.get());
    }
    return null;
  }

  @Override
  @Transactional
  public void addCartItem(long userId, long productId, Integer quantity)
      throws UserNotExistException, ProductNotExistException {
    // Retrieve UserEntity and Product
    UserEntity user = userService.getUserById(userId);
    Product product = productService.getProductEntityById(productId);
    // Check if the product and user exist
    if (user == null || product == null) {
      throw new UserNotExistException(Code.USER_NOT_FOUND);
    }
    // store in Redis :
    boolean storedInRedis = redisHashOperations.put(String.valueOf(userId),
        String.valueOf(productId), quantity);

    if (storedInRedis) {
      log.info("product stored in Redis: " + "userID : " + userId
          + " productId : " + productId + " quantity " + quantity);
    } else {
      log.error("Failed to store product in Redis: " + "userID : " + userId
          + " productId : " + productId);
    }

    // Check if there is already a cart item for the user and product
    cartItemRepository.findByUser_UidAndProduct_Pid(userId, productId)
        .ifPresent(existingCartItem -> {
          // If the cart item already exists, update the quantity
          existingCartItem.setQuantity(
              existingCartItem.getQuantity().add(BigDecimal.valueOf(quantity)));
          cartItemRepository.save(existingCartItem);
        });

    // If the cart item does not exist, create a new one
    if (!cartItemRepository.findByUser_UidAndProduct_Pid(userId, productId)
        .isPresent()) {
      CartItem newCartItem = CartItem.builder().user(user).product(product)
          .quantity(BigDecimal.valueOf(quantity)).build();
      cartItemRepository.save(newCartItem);
    }
  }


  @Override
  public Optional<List<CartItem>> findCartEntityByUserUid(Long uid) {
    List<CartItem> cartItems = cartItemRepository.findAllByUserUid(uid).get();

    // Check if the list is empty
    if (cartItems.isEmpty()) {
      return Optional.empty();
    } else {
      return Optional.of(cartItems.stream()//
          .collect(Collectors.toList()));
    }
  }

  @Override
  public boolean checkStock(Product productEntity, Integer quantity) {
    return quantity <= productEntity.getUnitStock()
        && productEntity.getUnitStock() != 0;
  }
}
