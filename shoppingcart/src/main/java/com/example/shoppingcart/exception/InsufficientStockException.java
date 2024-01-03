package com.example.shoppingcart.exception;

import com.example.shoppingcart.exception.setting.Code;

public class InsufficientStockException extends BusinessException {

  public InsufficientStockException(Code code) {
    super(code);
  }

}
