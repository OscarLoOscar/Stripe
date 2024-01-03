package com.example.shoppingcart.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.shoppingcart.entity.TransactionProduct;

public interface TransactionProductRepository
                extends JpaRepository<TransactionProduct, Long> {

        @Query(value = "SELECT * FROM transaction_product tp WHERE tp.tid = :tid",
                        nativeQuery = true)
        List<TransactionProduct> findAllByTid(@Param("tid") Long tid);

        @Query(value = "SELECT * FROM transaction_product tp WHERE tp.pid = :pid",
                        nativeQuery = true)
        TransactionProduct findByPid(@Param("pid") Long pid);

        TransactionProduct findByTpid(Long tpid);


}
