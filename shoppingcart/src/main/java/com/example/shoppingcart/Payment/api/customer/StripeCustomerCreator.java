package com.example.shoppingcart.Payment.api.customer;

import org.springframework.stereotype.Service;
import com.stripe.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class StripeCustomerCreator {

    private final StripeCustomerConverter stripeCustomerConverter;

    public Customer createStripeCustomer(UserEntity authorizedUser, String paymentMethodToken) {
        try {
            log.info("Create stripe customer: in progress: start stripe customer creation");
            CustomerCreateParams customerCreateParams = stripeCustomerConverter.toStripeObject(authorizedUser, paymentMethodToken);
            Customer createdStripeCustomer = Customer.create(customerCreateParams);
            String createdStripeCustomerId = createdStripeCustomer.getId();
            log.info("Create stripe customer: successful: stripe customer was created with createdStripeCustomerId = {}.", createdStripeCustomerId);

            return createdStripeCustomer;

        } catch (StripeException e) {
            log.error("Process stripe customer: failed: stripe customer was not created");
            throw new StripeCustomerProcessingException(authorizedUser.getEmail());
        }
    }
}