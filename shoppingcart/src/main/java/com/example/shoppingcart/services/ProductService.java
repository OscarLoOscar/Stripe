package com.example.shoppingcart.services;

import java.util.List;
import com.example.shoppingcart.entity.Product;
import com.example.shoppingcart.exception.ProductNotExistException;
import com.example.shoppingcart.model.ProductData;

public interface ProductService {
  ProductData addProduct(Product product);

  List<ProductData> getAllProduct();

  ProductData getProductById(Long productId) throws ProductNotExistException;

  Product getProductEntityById(Long productId) throws ProductNotExistException;

  ProductData editProduct(Product product);

  ProductData editProductPrice(Long productId, double price);

  ProductData updateProductstock(Long productId, int unitStock);

  void deleteProductById(Long productId);

  boolean isEnoughStock(Long pid, Integer quantity);

}
