package com.example.shoppingcart.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
import com.example.shoppingcart.services.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import com.example.shoppingcart.services.ProductService;
import com.example.shoppingcart.services.TransactionProductService;

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
    // return cartItemRepository.findAllByUserUid(uid)//
    // .stream()//
    // .map(cartItem -> {
    // CartItemData cartItemData = Mapper.map(cartItem);
    // cartItemData.setStock(cartItem.getProduct().getUnitStock());
    // return cartItemData;
    // })//
    // .collect(Collectors.toList());
    List<CartItem> cartItems = cartItemRepository.findAllByUserUid(uid).get();

    // Check if the list is empty
    if (cartItems.isEmpty()) {
      return Optional.empty();
    } else {
      return Optional.of(cartItems.stream()//
          .map(Mapper::map)//
          .collect(Collectors.toList()));
    }
    // List<CartItemData> output = new ArrayList<>();
    // for (CartItem cartItem : cartItemRepository.findAllByUserUid(uid)) {
    // CartItemData convent = Mapper.map(cartItem);
    // convent.setStock(cartItem.getProduct().getUnitStock());
    // output.add(convent);
    // }
    // return output;
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
  // List<CartItemData> output = new ArrayList<>();
  // for (CartItem cartItem : cartItemRepository.findAllByUserUid(pid)) {
  // CartItemData convent = Mapper.map(cartItem);
  // convent.setStock(cartItem.getProduct().getUnitStock());
  // output.add(convent);
  // }
  // return output;
  // }

  public ProductData getProductById(Long productId)
      throws ProductNotExistException {
    return productService.getProductById(productId);
  }

  // public UserData findUserByName(String name) {
  // return userService.findUserByUserName(name);
  // }

  /*
   * 在使用 merge 時，確保它發生在一個事務中。這通常需要使用 @Transactional 注解。在你的服務方法上添加 @Transactional 注解，這樣 Spring 會在方法執行時啟動一個事務。
   */
  // @Override
  // @Transactional
  // public boolean updateCartQuantity(long userId, long pid, int quantity) {
  // UserData userData = userService.getUserById(userId);

  // List<CartItem> cartItems =
  // cartItemRepository.findAllByUserUid(userId).get();

  // if (!cartItems.isEmpty()) {
  // CartItem cartItem2 = getEntityByUidAndPid(userId, pid);
  // cartItem2.setQuantity(
  // cartItem2.getQuantity().add(BigDecimal.valueOf(quantity)));
  // cartItem2 = entityManager.merge(cartItem2);
  // cartItemRepository.save(cartItem2);
  // } else {
  // ProductData productEntity = productService.getProductById(pid);
  // CartItem cartItementity = CartItem.builder()//
  // .product(Mapper.map(productEntity))//
  // .user(Mapper.map(userData))//
  // .quantity(BigDecimal.valueOf(quantity))//
  // .build();
  // cartItementity = entityManager.merge(cartItementity);
  // cartItemRepository.save(cartItementity);
  // // bug
  // // Update the cartItems list with the newly created entity
  // cartItems.add(cartItementity);
  // }
  // return true;
  // }


  // old
  // public CartItem getEntityByUidAndPid(Long uid, Long pid) {
  // return cartItemRepository.findByUser_UidAndProduct_Pid(uid, pid)//
  // .orElseThrow(() -> new RuntimeException(
  // "CartItem not found for uid: " + uid + " and pid: " + pid));
  // }
  public Optional<CartItem> getEntityByUidAndPid(Long uid, Long pid) {
    return cartItemRepository.findByUser_UidAndProduct_Pid(uid, pid);
  }

  public boolean isEnoughStock(Long pid, Integer quantity) {
    return productService.isEnoughStock(pid, quantity);
  }

  // @Override
  // public void addCartItem(CartItem cartItem) {
  // cartItemRepository.save(cartItem);
  // }

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
  public void addCartItem(long userId, long productId, int quantity)
      throws UserNotExistException, ProductNotExistException {
    // Retrieve UserEntity and Product
    UserEntity user = userService.getUserById(userId);
    Product product = productService.getProductEntityById(productId);
    // Check if the product and user exist
    if (user == null || product == null) {
      throw new UserNotExistException(Code.USER_NOT_FOUND);
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
