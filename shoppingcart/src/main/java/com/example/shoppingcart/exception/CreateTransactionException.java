package com.example.shoppingcart.exception;

import com.example.shoppingcart.exception.setting.Code;

public class CreateTransactionException extends BusinessException {

  public CreateTransactionException(Code code) {
    super(code);
  }
  
}
