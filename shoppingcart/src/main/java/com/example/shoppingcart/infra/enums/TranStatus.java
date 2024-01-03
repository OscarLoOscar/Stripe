package com.example.shoppingcart.infra.enums;

public enum TranStatus {
  ORDERED(0), //
  PREPARE(1), //
  PROCESSING(2), //
  SUCCESS(3), //
  FINISH(4), //
  NOT_SUCCESS(5),//
  ;//

  int code;

  private TranStatus(int code) {
    this.code = code;
  }

  public int getCode() {
    return this.code;
  }

  public TranStatus getTranStatus(int orderStatus) {
    for (TranStatus status : TranStatus.values()) {
      if (status.getCode() == orderStatus)
        return status;
    }
    return null;
  }

  public TranStatus nextStatus() {
    int nextStatusCOde = this.getCode() < 5 ? this.code + 1 : this.code;
    return getTranStatus(nextStatusCOde);
  }

  public boolean isForwaredStatus(TranStatus transactionStatus) {
    return this.getCode() < transactionStatus.getCode();
  }
}
