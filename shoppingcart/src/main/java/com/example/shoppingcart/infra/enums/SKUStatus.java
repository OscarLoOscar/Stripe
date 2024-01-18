package com.example.shoppingcart.infra.enums;

import lombok.Getter;

@Getter
public enum SKUStatus {
  NORMAL(0, "SKU_NORMAL"), //
  DELETED(1, "SKU_DELETED"), //
  PROHIBIT(-1, "SKU_PROHIBIT");

  private Integer code;

  private String message;

  SKUStatus(Integer code, String message) {
    this.code = code;
    this.message = message;
  }
}
