package com.example.shoppingcart.services;

import java.util.List;
import com.example.shoppingcart.entity.TransactionProduct;

public interface TransactionProductService {

  void save(TransactionProduct UserOrder);

  List<TransactionProduct> findAllTransactionProductByTransactionId(Long tid);

  TransactionProduct findByProductId(Long pid);

  TransactionProduct findByTranProductId(Long tpid);

  void deleteByProductId(Long pid);
}
