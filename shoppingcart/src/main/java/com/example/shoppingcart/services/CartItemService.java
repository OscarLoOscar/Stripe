package com.example.shoppingcart.services;

import java.util.List;
import java.util.Optional;
import com.example.shoppingcart.entity.CartItem;
import com.example.shoppingcart.entity.Product;
import com.example.shoppingcart.exception.CartItemNotFoundException;
import com.example.shoppingcart.exception.ProductNotExistException;
import com.example.shoppingcart.exception.UserNotExistException;
import com.example.shoppingcart.model.CartItemData;

public interface CartItemService {

        Optional<List<CartItemData>> findAllByUserUid(Long uid);

        Optional<List<CartItemData>> getUserCartItemsByProductId(Long pid);

        Optional<List<CartItem>> findCartEntityByUserUid(Long uid);

        // void addCartItem(CartItem cartItem);

        void addCartItem(long userId, long pid, int quantity)
                        throws UserNotExistException, ProductNotExistException;

        boolean updateCartQuantity(long userId, long pid, int quantity)
                        throws ProductNotExistException, UserNotExistException;

        CartItemData getCartItemDetails(long userId, long productId);

        void deleteCartItemByCartItemId(long userid, long cartItemId)
                        throws UserNotExistException, CartItemNotFoundException;

        void deleteAllCartItem();

        public boolean checkStock(Product productEntity, Integer quantity);
}
