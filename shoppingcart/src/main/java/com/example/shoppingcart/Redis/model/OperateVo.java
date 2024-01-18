package com.example.shoppingcart.Redis.model;

import java.util.List;
import lombok.Data;

@Data
public class OperateVo {
  private String userId;
  private String skuId;
  private Integer num;
  private List<String> skuIds;
}
