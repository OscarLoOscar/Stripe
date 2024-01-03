package com.example.shoppingcart.services;

import java.util.List;
import com.example.shoppingcart.entity.Transaction;
import com.example.shoppingcart.model.TransactionData;

public interface TransactionService {
  TransactionData createTransaction(Long userId);

  TransactionData getTransactionDetailByTransactionId(Long tid, Long uid);

  Transaction getTransactionByTransactionId(Long transactionId);

  boolean updateTransactionToProcessing(Long tid, Long uid);

  List<Transaction> findAllByBuyerUid(Long uid);

  TransactionData findByTidAndUid(Long tid, Long uid);

  TransactionData finishTransaction(Long tid, Long uid);
}
