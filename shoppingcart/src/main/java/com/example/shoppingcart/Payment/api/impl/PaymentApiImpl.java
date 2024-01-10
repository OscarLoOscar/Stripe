// package com.example.shoppingcart.payment.api.impl;

// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.stereotype.Service;

// @Slf4j
// @Service
// @RequiredArgsConstructor
// public class PaymentApiImpl implements PaymentApi {

//     private final PaymentRetriever paymentRetriever;
//     private final PaymentProcessor paymentProcessor;
//     private final PaymentEventProcessor paymentEventProcessor;
//     private final CardDetailsProcessor cardDetailsProcessor;

//     @Override
//     public ProcessedPaymentWithClientSecretDto processPayment(final String cardDetailsTokenId) {
//         return paymentProcessor.processPayment(cardDetailsTokenId);
//     }

//     @Override
//     public ProcessedPaymentDetailsDto getPaymentDetails(final Long paymentId) {
//         return paymentRetriever.getPaymentDetails(paymentId);
//     }

//     @Override
//     public void processPaymentEvent(final String paymentIntentPayload, final String stripeSignatureHeader) {
//         paymentEventProcessor.processPaymentEvent(paymentIntentPayload, stripeSignatureHeader);
//     }

//     @Override
//     public String processCardDetailsToken(CreateCardDetailsTokenRequest createCardDetailsTokenRequest) {
//         return cardDetailsProcessor.processCardDetails(createCardDetailsTokenRequest);
//     }
// }
