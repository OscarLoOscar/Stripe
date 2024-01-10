// package com.example.shoppingcart.payment.api.impl.intent;

// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Isolation;
// import org.springframework.transaction.annotation.Propagation;
// import org.springframework.transaction.annotation.Transactional;
// import java.util.Objects;
// import java.util.UUID;

// /**
//  * This class is responsible for retrieving relevant payment details from database
//  * */

// @Slf4j
// @RequiredArgsConstructor
// @Service
// public class PaymentRetriever {

//     private final ShoppingCartRepository shoppingCartRepository;
//     private final PaymentRepository paymentRepository;
//     private final PaymentConverter paymentConverter;

//     @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
//     public ProcessedPaymentDetailsDto getPaymentDetails(final Long paymentId) {
//         Objects.requireNonNull(paymentId);
//         log.info("Get payment details: starting: payment details retrieve by payment id = {}.", paymentId);

//         return paymentRepository.findById(paymentId)
//                 .map(payment -> {
//                     UUID shoppingCartId = payment.getShoppingCartId();
//                     return shoppingCartRepository.findById(shoppingCartId)
//                             .map(shoppingCart -> paymentConverter.toDto(payment, shoppingCart.getItems()))
//                             .orElseThrow(() -> new ShoppingCartNotFoundException(shoppingCartId));
//                 })
//                 .orElseThrow(() -> new PaymentNotFoundException(paymentId));
//     }
// }
