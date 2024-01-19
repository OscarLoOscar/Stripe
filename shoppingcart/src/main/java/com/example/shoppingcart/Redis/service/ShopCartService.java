package com.example.shoppingcart.Redis.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.example.shoppingcart.Redis.model.OperateVo;
import com.example.shoppingcart.entity.Product;
import com.example.shoppingcart.exception.CartItemNotFoundException;
import com.example.shoppingcart.exception.ProductNotExistException;
import com.example.shoppingcart.exception.UserNotExistException;
import com.example.shoppingcart.exception.setting.ApiResp;
import com.example.shoppingcart.model.CartItemData;

public interface ShopCartService {

        Optional<List<CartItemData>> findAllByUserUid(Long uid);

        Optional<List<CartItemData>> getUserCartItemsByProductId(Long pid);

        List<CartItemData> getAll(String key);

        boolean addCartItem(long userId, long pid, Integer quantity)
                        throws UserNotExistException, ProductNotExistException;

        boolean updateCartQuantity(long userId, long pid, int quantity)
                        throws ProductNotExistException, UserNotExistException;

        CartItemData getCartItemDetails(long userId, long productId);

        void deleteCartItemByCartItemId(long userid, long cartItemId)
                        throws UserNotExistException, CartItemNotFoundException;

        void deleteAllCartItem(String userId);

        public boolean checkStock(Product productEntity, Integer quantity);
}
