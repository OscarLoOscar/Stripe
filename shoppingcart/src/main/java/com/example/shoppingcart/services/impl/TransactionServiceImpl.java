package com.example.shoppingcart.services.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.shoppingcart.entity.Product;
import com.example.shoppingcart.entity.Transaction;
import com.example.shoppingcart.entity.TransactionProduct;
import com.example.shoppingcart.infra.enums.TranStatus;
import com.example.shoppingcart.model.CartItemData;
import com.example.shoppingcart.model.Mapper;
import com.example.shoppingcart.model.ProductData;
import com.example.shoppingcart.model.TransactionData;
import com.example.shoppingcart.model.TransactionProductData;
import com.example.shoppingcart.repository.TransactionRepository;
import com.example.shoppingcart.services.ProductService;
import com.example.shoppingcart.services.TransactionService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

  private final UserServiceImpl userService;
  private final TransactionRepository transactionRepository;
  private final CartItemServiceImpl cartItemService;
  private final TransactionProductServiceImpl transactionProductServiceImpl;
  private final ProductService productService;

  @Autowired
  public TransactionServiceImpl(UserServiceImpl userService,
      TransactionRepository transactionRepository,
      CartItemServiceImpl cartItemService,
      TransactionProductServiceImpl transactionProductServiceImpl,
      ProductService productService) {
    this.userService = userService;
    this.transactionRepository = transactionRepository;
    this.cartItemService = cartItemService;
    this.transactionProductServiceImpl = transactionProductServiceImpl;
    this.productService = productService;
  }

  @Transactional
  @Override
  public TransactionData createTransaction(Long userId) {
    BigDecimal finalSum = BigDecimal.ZERO;
    for (CartItemData data : cartItemService.findAllByUserUid(userId).get()) {
      BigDecimal total = BigDecimal.ZERO;
      total = data.getQuantity().multiply(data.getPrice());
      finalSum = finalSum.add(total);
    }
    // for Transaction DataBase
    Transaction recode = Transaction.builder()//
        .user(userService.getUserById(userId))//
        .datetime(LocalDateTime.now())//
        .status(TranStatus.PREPARE)//
        .totalPrice(finalSum)//
        .build();
    transactionRepository.save(recode);
    TransactionData output = Mapper.map(recode);

    List<CartItemData> listOfCD = new ArrayList<>();
    for (CartItemData cartItemData : cartItemService.findAllByUserUid(userId)
        .get()) {
      listOfCD.add(cartItemData);
    }
    // DONE

    // for Transaction_product DataBase
    List<TransactionProductData> listOfTPD = new ArrayList<>();
    for (CartItemData cartItemData : cartItemService.findAllByUserUid(userId)
        .get()) {
      TransactionProductData transactionProductData =
          TransactionProductData.builder()//
              .tid(recode.getTid())//
              .cartItemData(cartItemData)//
              .quantity(cartItemData.getQuantity())//
              .totalPrice(BigDecimal.valueOf(cartItemData.getStock())
                  .multiply(cartItemData.getPrice()))//
              .build();
      // for new TransactionProduct to DB
      TransactionProduct transactionProduct =
          Mapper.map(transactionProductData);

      // 19-12-2023 , set transaction into transactionProduct
      transactionProduct.setTransaction(recode);
      transactionProductServiceImpl.save(transactionProduct);

      // after save to DB , get primary key , set back to DTO
      transactionProductData.setTpid(transactionProduct.getTpid());

      // log.info("CHECKING transactionProduct : " + transactionProduct);
      transactionProductData.setCartItemData(cartItemData);
      listOfTPD.add(transactionProductData);
    }
    output.setItems(listOfTPD);
    // Add at 19/12/2023 , clear the shopping cart
    cartItemService.deleteAllCartItem();
    return output;

  }

  @Override
  public Transaction getTransactionByTransactionId(Long transactionId) {
    return transactionRepository.findById(transactionId).orElse(null);
  }

  @Override
  public List<Transaction> findAllByBuyerUid(Long uid) {
    return transactionRepository.findAllByBuyerUid(uid);
  }

  @Override
  public TransactionData findByTidAndUid(Long tid, Long uid) {
    return Mapper.map(transactionRepository.findByTidAndUid(tid, uid));
  }

  @Override
  public TransactionData getTransactionDetailByTransactionId(Long tid,
      Long uid) {
    // 2. get all transaction by userId
    TransactionData tList = this.findByTidAndUid(tid, uid);
    // 3.get transaction product by tid
    List<TransactionProduct> tpList = transactionProductServiceImpl
        .findAllTransactionProductByTransactionId(tid);

    // 4.get all cart_item by userId
    // List<CartItemData> cList = cartItemService.findAllByUserUid(uid).get();
    List<ProductData> pList = productService.getAllProduct();

    // 5.convent from List<TransactionProduct> to List<TransactionProductData>
    List<TransactionProductData> items = new ArrayList<>();

    BigDecimal total = BigDecimal.ZERO;
    // CartItemData matchedCartItem = null;
    for (TransactionProduct tp : tpList) {
      Transaction t = tp.getTransaction();
      ProductData matchedCartItem = findMatchingCartItem(tp, t, pList);
      // Create a new TransactionProductData instance for each iteration
      TransactionProductData transactionProductData = Mapper.map(tp);
      transactionProductData.setTid(tid);

      if (matchedCartItem != null) {
        items.add(transactionProductData);
        total = total.add(BigDecimal.valueOf(matchedCartItem.getProductPrice())
            .multiply(transactionProductData.getQuantity()));
      }
    }
    // 6.ensure correct placement of return statement

    if (!items.isEmpty()) {
      // Assuming total calculation is correct based on matchedCartItem
      // BigDecimal total =
      // matchedCartItem.getPrice().multiply(items.get(0).getQuantity());

      return TransactionData.builder()//
          .transactionId(tid)//
          .buyerUid(uid)//
          .datetime(tList.getDatetime())//
          .status(tList.getStatus())//
          .total(total.setScale(2))//
          .items(items)//
          .build();
    }

    return TransactionData.builder()//
        .transactionId(tid)//
        .buyerUid(uid)//
        .datetime(tList.getDatetime())//
        .status(tList.getStatus())//
        .total(total.setScale(2))//
        .items(items)//

        .build();
  }

  // 7.Encapsulate for finding match cart item
  private ProductData findMatchingCartItem(TransactionProduct tp, Transaction t,
      List<ProductData> cList) {
    for (ProductData c : cList) {
      if (c.getPid().equals(tp.getPid())
          && tp.getTransaction().getTid().equals(t.getTid())) {
        return c;
      }
    }
    return null;
  }


  @Override
  public boolean updateTransactionToProcessing(Long tid, Long uid) {
    // Fetch the transaction By ID
    Transaction transaction = this.getTransactionByTransactionId(tid);

    if (transaction != null) {
      // update the transaction status to PROCESSING
      transaction.setStatus(TranStatus.PROCESSING);
      transactionRepository.save(transaction);
      return true;
    }
    return false;
  }

  @Override
  public TransactionData finishTransaction(Long tid, Long uid) {
    // Fetch the transaction By ID
    Transaction transaction = this.getTransactionByTransactionId(tid);

    // 3.get transaction product by tid
    List<TransactionProduct> tpList = transactionProductServiceImpl
        .findAllTransactionProductByTransactionId(tid);
    // 4.get all cart_item by userId
    List<ProductData> pList = productService.getAllProduct();

    if (transaction != null) {
      // update the transaction status to FINISH
      transaction.setStatus(TranStatus.FINISH);

      transactionRepository.save(transaction);

      // return a success response
      // return ResponseEntity.ok(Mapper.map(transaction));
      List<TransactionProductData> items = new ArrayList<>();

      ProductData matchedCartItem = null;
      for (TransactionProduct tp : tpList) {
        Transaction t = tp.getTransaction();
        matchedCartItem = findMatchingCartItem(tp, t, pList);

        // Create a new TransactionProductData instance for each iteration
        TransactionProductData transactionProductData = Mapper.map(tp);
        if (matchedCartItem != null) {
          items.add(transactionProductData);
        }
      }

      return TransactionData.builder()//
          .transactionId(tid)//
          .buyerUid(uid)//
          .datetime(transaction.getDatetime())//
          .status(transaction.getStatus())//
          .total(transaction.getTotalPrice())//
          .items(items)//
          .build();

    }
    return null;
  }
}
