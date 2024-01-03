package com.example.shoppingcart.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.shoppingcart.entity.TransactionProduct;
import com.example.shoppingcart.repository.TransactionProductRepository;
import com.example.shoppingcart.services.TransactionProductService;

@Service
public class TransactionProductServiceImpl
    implements TransactionProductService {
  @Autowired
  TransactionProductRepository transactionProductRepository;

  @Override
  public void save(TransactionProduct UserOrder) {
    transactionProductRepository.save(UserOrder);
  }

  @Override
  public List<TransactionProduct> findAllTransactionProductByTransactionId(
      Long tid) {
    return transactionProductRepository.findAllByTid(tid);
  }

  @Override
  public TransactionProduct findByProductId(Long pid) {
    return transactionProductRepository.findByPid(pid);
  }

  @Override
  public TransactionProduct findByTranProductId(Long tpid) {
    return transactionProductRepository.findByTpid(tpid);
  }

  @Override
  public void deleteByProductId(Long pid) {
    transactionProductRepository.deleteById(pid);
  }
}
