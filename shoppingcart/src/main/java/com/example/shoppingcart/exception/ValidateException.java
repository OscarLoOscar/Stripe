package com.example.shoppingcart.exception;

import com.example.shoppingcart.exception.setting.Code;
import jakarta.validation.ConstraintViolationException;

public class ValidateException extends ConstraintViolationException {
  public ValidateException(Code code) {
    super(code.getDesc(), null);
  }
}
