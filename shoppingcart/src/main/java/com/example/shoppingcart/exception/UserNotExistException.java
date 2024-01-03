package com.example.shoppingcart.exception;

import com.example.shoppingcart.exception.setting.Code;

public class UserNotExistException extends BusinessException {
  public UserNotExistException(Code code) {
    super(code);
  }
}
