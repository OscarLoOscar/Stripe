// package com.example.shoppingcart.payment.api.impl.event;

// import com.example.shoppingcart.payment.config.StripeConfiguration;
// import com.example.shoppingcart.payment.exception.PaymentEventProcessingException;
// import com.stripe.exception.SignatureVerificationException;
// import com.stripe.model.Event;
// import com.stripe.net.Webhook;
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.stereotype.Service;

// /**
//  * This class is responsible for payment event (stripe object) creation.
//  * */

// @Slf4j
// @RequiredArgsConstructor
// @Service
// public class PaymentEventCreator {

//     private final StripeConfiguration stripeConfig;

//     public Event createPaymentEvent(String paymentIntentPayload, String stripeSignatureHeader) {
//         log.info("Create payment event: start payment event creation:" +
//                 " paymentIntentPayload: {}, stripeSignatureHeader: {}.", paymentIntentPayload, stripeSignatureHeader);
//         try {
//             return Webhook.constructEvent(paymentIntentPayload, stripeSignatureHeader, stripeConfig.webHookSecretKey());
//         } catch (SignatureVerificationException ex) {
//             log.error("Error during payment event creating", ex);
//             throw new PaymentEventProcessingException(stripeSignatureHeader);
//         }
//     }
// }
