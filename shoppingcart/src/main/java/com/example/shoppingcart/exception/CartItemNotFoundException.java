package com.example.shoppingcart.exception;

import com.example.shoppingcart.exception.setting.Code;

public class CartItemNotFoundException extends BusinessException {
  public CartItemNotFoundException(Code code) {
    super(code);
  }
}
