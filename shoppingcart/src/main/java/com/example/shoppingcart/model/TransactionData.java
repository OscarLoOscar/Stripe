package com.example.shoppingcart.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import com.example.shoppingcart.infra.enums.TranStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Builder
@JsonPropertyOrder({"tid", "buyer_uid", "datetime", "status", "total", "items"})
public class TransactionData {
  @JsonProperty("tid")
  Long transactionId;

  @JsonProperty("buyer_uid")
  Long buyerUid;

  @Column(name = "datetime")
  @JsonFormat(locale = "zh", timezone = "GMT+8",
      pattern = "yyyy-MM-dd HH:mm:ss")
  @DateTimeFormat
  LocalDateTime datetime;

  TranStatus status;

  BigDecimal total;

  @JsonProperty("items")
  List<TransactionProductData> items;

}
