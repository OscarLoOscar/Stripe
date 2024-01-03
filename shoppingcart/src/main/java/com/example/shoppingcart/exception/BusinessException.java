package com.example.shoppingcart.exception;

import com.example.shoppingcart.exception.setting.Code;

public class BusinessException extends Exception {

  private Code code;

  public int getCode() {
    return this.code.getCode();
  }
  
  public BusinessException(Code code) {
    super(code.getDesc());
    this.code = code;
  }

}
