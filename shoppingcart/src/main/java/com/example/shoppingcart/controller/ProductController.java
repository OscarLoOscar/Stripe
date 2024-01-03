package com.example.shoppingcart.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.example.shoppingcart.exception.ProductNotExistException;
import com.example.shoppingcart.model.ProductData;
import com.example.shoppingcart.model.request.ProductRequest;

public interface ProductController {

  @PostMapping("/addProduct")
  @ResponseStatus(HttpStatus.CREATED)
  ProductData addProduct(@RequestBody ProductRequest product);

  @GetMapping("/product")
  @ResponseStatus(HttpStatus.OK)
  List<ProductData> getAllProduct();

  @GetMapping("/product/{productId}")
  @ResponseStatus(HttpStatus.OK)
  ProductData getProductById(@PathVariable String productId)
      throws ProductNotExistException;

  @DeleteMapping("/deleteProduct/{productId}")
  @ResponseStatus(HttpStatus.OK)
  void deleteProductById(@PathVariable String productId);

  @PutMapping("/editProduct")
  @ResponseStatus(HttpStatus.CREATED)
  ProductData editProduct(@RequestBody ProductRequest product)
      throws ProductNotExistException;

  @PatchMapping("/editProductPrice/{productId}")
  @ResponseStatus(HttpStatus.CREATED)
  ProductData editProductPrice(@PathVariable String productId)
      throws ProductNotExistException;

}
