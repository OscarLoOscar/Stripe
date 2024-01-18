package com.example.shoppingcart.Redis.service;

import java.util.Map;
import com.example.shoppingcart.Redis.model.OperateVo;
import com.example.shoppingcart.exception.setting.ApiResp;

public interface ShopCartService {

    ApiResp<Boolean> addCart(OperateVo vo);

    ApiResp<Map<Object, Object>> showCart(OperateVo vo);

    ApiResp<Boolean>updateCartNum(OperateVo vo);

    ApiResp<Boolean> delCart(OperateVo vo);

    ApiResp<Boolean> checkNumCart(OperateVo vo);
}
