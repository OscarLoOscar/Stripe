package com.example.shoppingcart.exception;

import com.example.shoppingcart.exception.setting.Code;

public class NoStockException extends BusinessException{

  public NoStockException(Code code) {
    super(code);
  }
  
}
