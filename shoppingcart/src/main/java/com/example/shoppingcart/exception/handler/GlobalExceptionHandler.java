package com.example.shoppingcart.exception.handler;

import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.example.shoppingcart.exception.BusinessException;
import com.example.shoppingcart.exception.ProductNotExistException;
import com.example.shoppingcart.exception.UserNotExistException;
import com.example.shoppingcart.exception.setting.ApiResp;
import com.example.shoppingcart.exception.setting.Code;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(value = BusinessException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResp<Void> finnhubExceptionHandler(BusinessException e) {
    return ApiResp.<Void>builder() //
        .status(Code.fromCode(e.getCode())) //
        .concatMessageIfPresent(e.getMessage())//
        // .data(null) //
        .build();
  }

  @ExceptionHandler(value = UserNotExistException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResp<Void> finnhubExceptionHandler(UserNotExistException e) {
    return ApiResp.<Void>builder() //
        .status(Code.fromCode(e.getCode())) //
        .concatMessageIfPresent(e.getMessage())//
        // .data(null) //
        .build();
  }

  @ExceptionHandler(value = ProductNotExistException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResp<Void> finnhubExceptionHandler(ProductNotExistException e) {
    return ApiResp.<Void>builder() //
        .status(Code.fromCode(e.getCode())) //
        .concatMessageIfPresent(e.getMessage())//
        // .data(null) //
        .build();
  }

  @ExceptionHandler(value = NoSuchElementException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResp<Void> finnhubExceptionHandler(NoSuchElementException e) {
    return ApiResp.<Void>builder() //
        .status(getRespCode(e))//
        .concatMessageIfPresent(e.getMessage())//
        // .data(null) //
        .build();
  }

  @ExceptionHandler(value = RuntimeException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResp<Void> runtimeExceptionHandler(RuntimeException e) {
    return ApiResp.<Void>builder() //
        .status(getRespCode(e)) //
        .concatMessageIfPresent(e.getMessage())//
        // .data(null) //
        .build();
  }

  @ExceptionHandler(value = Exception.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResp<Void> exceptionHandler(Exception e) {
    return ApiResp.<Void>builder() //
        .status(getRespCode(e)) //
        .concatMessageIfPresent(e.getMessage())//
        // .data(null) //
        .build();
  }

  private static Code getRespCode(Exception e) {
    if (e instanceof IllegalArgumentException) {
      return Code.IAE_EXCEPTION;
    }
    if(e instanceof NoSuchElementException) {
      return Code.NOT_FOUND;
    }
    if(e instanceof UserNotExistException) {
      return Code.USER_NOT_FOUND;
    }
 
    // ...
    return null;
//    return Code.NOT_FOUND;
  }
}
