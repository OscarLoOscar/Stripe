package com.example.shoppingcart.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.shoppingcart.entity.Product;
import com.example.shoppingcart.exception.ProductNotExistException;
import com.example.shoppingcart.exception.setting.Code;
import com.example.shoppingcart.model.Mapper;
import com.example.shoppingcart.model.ProductData;
import com.example.shoppingcart.repository.ProductRepository;
import com.example.shoppingcart.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

  ProductRepository productRepository;

  @Autowired
  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public ProductData addProduct(Product product) {
    return Mapper.map(productRepository.save(product));
  }

  @Override
  public List<ProductData> getAllProduct() {
    List<ProductData> output = new ArrayList<>();
    for (Product product : productRepository.findAll()) {
      output.add(Mapper.map(product));
    }
    return output;
  }

  @Override
  public ProductData getProductById(Long productId)
      throws ProductNotExistException {
    return Mapper.map(productRepository.findById(productId)//
        .orElseThrow(
            () -> new ProductNotExistException(Code.PRODUCT_NOT_EXIST)));
  }

  @Override
  public void deleteProductById(Long productId) {
    productRepository.delete(productRepository.findById(productId).get());
  }

  @Override
  public ProductData editProduct(Product product) {
    Optional<Product> change =
        productRepository.findById(product.getProductId());
    if (change.isPresent()) {
      return Mapper.map(change.get());
    } else {
      return null;
    }
  }

  @Override
  public ProductData editProductPrice(Long productId, double price) {
    Product before = productRepository.findById(productId).get();
    before.setProductPrice(BigDecimal.valueOf(price));
    productRepository.save(before);
    return Mapper.map(before);
  }

  @Override
  public ProductData updateProductstock(Long productId, int unitStock) {
    Product before = productRepository.findById(productId).get();
    before.setUnitStock(unitStock);
    productRepository.save(before);
    return Mapper.map(before);
  }

  @Override
  public boolean isEnoughStock(Long pid, Integer quantity) {
    return productRepository.findById(pid).get().getUnitStock() >= quantity;
  }

  @Override
  public Product getProductEntityById(Long productId)
      throws ProductNotExistException {
    return productRepository.findById(productId)//
        .orElseThrow(
            () -> new ProductNotExistException(Code.PRODUCT_NOT_EXIST));
  }
}
