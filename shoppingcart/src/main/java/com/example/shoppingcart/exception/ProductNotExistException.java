package com.example.shoppingcart.exception;

import com.example.shoppingcart.exception.setting.Code;

public class ProductNotExistException extends BusinessException {
  public ProductNotExistException(Code code) {
    super(code);
  }

}
