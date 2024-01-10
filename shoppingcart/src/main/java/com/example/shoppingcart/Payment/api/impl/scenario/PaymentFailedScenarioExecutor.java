// package com.example.shoppingcart.payment.api.impl.scenario;

// import com.stripe.model.Event;
// import com.stripe.model.PaymentIntent;
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Isolation;
// import org.springframework.transaction.annotation.Propagation;
// import org.springframework.transaction.annotation.Transactional;
// import java.util.Objects;


// /**
//  * This class is responsible for handling the fail scenario and updating
//  * in database record of payment, with the relevant status and description
//  * */

// @Slf4j
// @RequiredArgsConstructor
// @Service
// public class PaymentFailedScenarioExecutor implements PaymentScenarioExecutor {

//     private final PaymentRepository paymentRepository;

//     @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
//     public void execute(PaymentIntent paymentIntent) {
//         log.info("Handle payment scenario method: start of handling payment intent: {} by failed scenario.", paymentIntent);
//         paymentRepository.updateStatusAndDescriptionInPayment(paymentIntent.getId(), PAYMENT_IS_FAILED.toString(), PAYMENT_IS_FAILED.getDescription());
//         log.info("Handle payment scenario method: finish of handling payment intent: {} by failed scenario.", paymentIntent);
//     }

//     @Override
//     public boolean supports(Event event) {
//         return Objects.equals(PAYMENT_IS_FAILED.getStatus(), event.getType());
//     }
// }
