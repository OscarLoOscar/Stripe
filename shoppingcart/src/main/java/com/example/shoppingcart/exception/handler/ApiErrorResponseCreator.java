package com.example.shoppingcart.exception.handler;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.shoppingcart.exception.dto.ApiErrorResponse;

@Service
public class ApiErrorResponseCreator {

  public ApiErrorResponse buildResponse(String errorMessage,
      HttpStatus httpStatus) {
    return ApiErrorResponse.builder().message(errorMessage)
        .timestamp(LocalDateTime.now()).httpStatusCode(httpStatus.value())
        .build();
  }

  public ApiErrorResponse buildResponse(Exception exception,
      HttpStatus httpStatus) {
    return buildResponse(exception.getMessage(), httpStatus);
  }
}
