package com.example.shoppingcart.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.shoppingcart.entity.Transaction;

public interface TransactionRepository
    extends JpaRepository<Transaction, Long> {
  @Query(value = "SELECT * FROM transaction t WHERE t.uid=:uid",
      nativeQuery = true)
  List<Transaction> findAllByBuyerUid(@Param(value = "uid") Long uid);

  @Query(
      value = "SELECT * FROM transaction t WHERE t.tid=:tid AND t.uid=:uid ",
      nativeQuery = true)
  Transaction findByTidAndUid(@Param(value = "tid") Long tid,
      @Param(value = "uid") Long uid);
}
