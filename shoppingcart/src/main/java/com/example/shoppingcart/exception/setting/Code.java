package com.example.shoppingcart.exception.setting;

import lombok.Getter;

//SysCode
@Getter
public enum Code { 
  OK(20000, "OK"),
  // 40000 - 49999
  NOT_FOUND(40000, "Resource NOT FOUND."), //
  // Shop
  SHOP_NOT_ENOUGH_PRODUCT(41002, "Shop has not enough product."), //
  PRODUCT_NOT_EXIST(41003, "Product not exist."), //
  INSUFFICIENT_STOCK_EXCEPTION(41004,
      "Can't add more items to the cart than the available stock."), //
  CART_ITEM_NOT_FOUND_EXCEPTION(41005, "Item is not found in the cart."), //
  USER_NOT_FOUND(41006, "User not exists."), //
  CART_IS_EMPTY(41007, "Cart is empty"), //
  OUT_OF_STOCK(41008, "Out of Stock"), //
  // Server
  SERVER_TIMEOUT(50000, "Server Timeout."), //
  THIRD_PARTY_SERVER_UNAVAILABLE(50001, "Third Party Service Unavailable."), //
  REDIS_SERVER_UNAVAILABLE(50002, "Redis unavailable."),
  // RuntimeException: 90000 - 99999
  IAE_EXCEPTION(90000, "Illegal Argument Exception."), //
  ENTITY_NOT_FOUND(90001, "Entity Not Found."), //
  VALIDATOR_FAIL(90002, "Validator Fail.");

  private final int code;
  private final String desc;

  private Code(int code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public static Code fromCode(int code) {
    for (Code c : Code.values()) {
      if (c.code == code) {
        return c;
      }
    }
    throw new IllegalArgumentException(
        "No matching constant for code: " + code);
  }

}
