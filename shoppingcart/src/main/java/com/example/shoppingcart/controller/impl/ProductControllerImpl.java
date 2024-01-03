package com.example.shoppingcart.controller.impl;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.shoppingcart.controller.ProductController;
import com.example.shoppingcart.entity.Product;
import com.example.shoppingcart.exception.ProductNotExistException;
import com.example.shoppingcart.model.ProductData;
import com.example.shoppingcart.model.request.ProductRequest;
import com.example.shoppingcart.services.ProductService;

@RestController
@RequestMapping("/public")
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class ProductControllerImpl implements ProductController {

  ProductService productService;

  @Autowired
  public ProductControllerImpl(ProductService productService) {
    this.productService = productService;
  }

  @Override
  public ProductData addProduct(ProductRequest product) {
    Product entity = Product.builder()//
        .productName(product.getProductName())//
        .productDescription(product.getProductDescription())//
        .imageUrl(product.getImageUrl())//
        .productPrice(BigDecimal.valueOf(product.getProductPrice()))//
        .unitStock(product.getUnitStock())//
        .build();
    return productService.addProduct(entity);
  }

  @Override
  public List<ProductData> getAllProduct() {
    return productService.getAllProduct();
  }

  @Override
  public ProductData getProductById(String productId)
      throws ProductNotExistException {
    return productService.getProductById(Long.valueOf(productId));
  }

  @Override
  public void deleteProductById(String productId) {
    productService.deleteProductById(Long.valueOf(productId));
  }

  @Override
  public ProductData editProduct(ProductRequest product) {
    Product entity = Product.builder()//
        .productName(product.getProductName())//
        .productDescription(product.getProductDescription())//
        .imageUrl(product.getImageUrl())//
        .productPrice(BigDecimal.valueOf(product.getProductPrice()))//
        .unitStock(product.getUnitStock())//
        .build();
    return productService.editProduct(entity);
  }

  @Override
  public ProductData editProductPrice(String productId)
      throws ProductNotExistException {
    return productService.getProductById(Long.valueOf(productId));
  }

}
