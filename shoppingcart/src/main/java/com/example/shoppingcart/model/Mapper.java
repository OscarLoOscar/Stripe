package com.example.shoppingcart.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import com.example.shoppingcart.entity.UserEntity;
import com.example.shoppingcart.infra.enums.TranStatus;
import com.example.shoppingcart.entity.CartItem;
import com.example.shoppingcart.entity.Product;
import com.example.shoppingcart.entity.Transaction;
import com.example.shoppingcart.entity.TransactionProduct;

public class Mapper {

  public static UserData map(UserEntity userEntity) {
    return UserData.builder()//
        .userId(userEntity.getUserId())//
        .fireBaseUid(userEntity.getFireBaseUid())//
        .email(userEntity.getEmail())//
        .build();
  }

  public static ProductData map(Product product) {
    return ProductData.builder()//
        .pid(product.getProductId())//
        .productName(product.getProductName())//
        .productPrice(product.getProductPrice().doubleValue())//
        .productDescription(product.getProductDescription())//
        .productPrice(product.getProductPrice().doubleValue())//
        .imageUrl(product.getImageUrl())//
        .unitStock(product.getUnitStock())//
        .build();
  }

  public static Product map(ProductData product) {
    return Product.builder()//
        .productId(product.getPid())//
        .productName(product.getProductName())//
        .productPrice(BigDecimal.valueOf(product.getProductPrice()))//
        .productDescription(product.getProductDescription())//
        .imageUrl(product.getImageUrl())//
        .unitStock(product.getUnitStock())//
        .build();
  }

  public static CartItemData map(CartItem cartItem) {
    return CartItemData.builder()//
        // .cid(cartItem.getCartId())//
        .pid(cartItem.getProduct().getProductId())//
        .name(cartItem.getProduct().getProductName())//
        .imageUrl(cartItem.getProduct().getImageUrl())//
        .price(cartItem.getProduct().getProductPrice())
        .quantity(cartItem.getQuantity())//
        .description(cartItem.getProduct().getProductDescription())//
        .stock(cartItem.getProduct().getUnitStock())//
        .build();
  }

  public static CartItem map(Product product, UserEntity user) {
    return CartItem.builder()//
        .product(product)//
        .user(user)//
        .build();
  }


  public static UserEntity map(FireBaseUserData data) {
    return UserEntity.builder()//
        .fireBaseUid(data.getFirebaseUid())//
        .email(data.getEmail())//
        .build();
  }



  public static UserEntity map(UserData userEntity) {
    return UserEntity.builder()//
        .userId(userEntity.getUserId())//
        .fireBaseUid(userEntity.getFireBaseUid())//
        .email(userEntity.getEmail())//
        .build();
  }

  public static TransactionData map(Transaction transactionEntity) {
    return TransactionData.builder()//
        .transactionId(transactionEntity.getTid())//
        .buyerUid(transactionEntity.getUser().getUserId()) // Assuming there's a getUserId method in UserEntity
        .datetime(transactionEntity.getDatetime())
        .status(transactionEntity.getStatus()) // Adjust accordingly
        .total(transactionEntity.getTotalPrice().setScale(2))//
        .build();
  }

  public static TransactionProductData map(
      TransactionProduct transactionProduct) {
    CartItemData cartItemData = getCartItemData(transactionProduct);
    return TransactionProductData.builder()//
        .tpid(transactionProduct.getTpid())//
        .quantity(transactionProduct.getQuantity())//
        .cartItemData(cartItemData)//
        .totalPrice(transactionProduct.getPrice()//
            .multiply(transactionProduct.getQuantity()).setScale(2))//
        .build();
  }

  // Add a method to get CartItemData from TransactionProduct
  private static CartItemData getCartItemData(
      TransactionProduct transactionProduct) {
    return CartItemData.builder()//
        .pid(transactionProduct.getPid())//
        .name(transactionProduct.getName())//
        .imageUrl(transactionProduct.getImageUrl())//
        .price(transactionProduct.getPrice())//
        .quantity(transactionProduct.getQuantity())//
        .stock(transactionProduct.getStock())//
        .description(transactionProduct.getDescription())//
        .build();
  }

  public static TransactionProduct map(TransactionProductData tpd) {
    return TransactionProduct.builder()//
        .pid(tpd.getCartItemData().getPid())//
        .name(tpd.getCartItemData().getName())//
        .description(tpd.getCartItemData().getDescription())//
        .imageUrl(tpd.getCartItemData().getImageUrl())//
        .price(tpd.getCartItemData().getPrice())//
        .quantity(tpd.getQuantity())//
        .stock(tpd.getCartItemData().getStock())//
        .build();
  }
}
