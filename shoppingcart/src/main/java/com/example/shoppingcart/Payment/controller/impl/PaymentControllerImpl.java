package com.example.shoppingcart.payment.controller.impl;

import java.util.List;
import java.util.Map;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.shoppingcart.payment.model.RequestDTO;
import com.stripe.exception.StripeException;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

public interface PaymentControllerImpl {

    @PostMapping("/create-session/{tid}")
    public String createPaymentSession(
            @PathVariable(name = "tid") Long transactionId,JwtAuthenticationToken jwt);


    @PostMapping("/checkout/integrated")
    String integratedCheckout(@RequestBody RequestDTO requestDTO)
            throws StripeException;

    @PostMapping("/checkout/hosted")
    String hostedCheckOut(@RequestBody RequestDTO requestDTO)
            throws StripeException;

    @PostMapping("/subscriptions/new")
    String newSubscription(@RequestBody RequestDTO requestDTO)
            throws StripeException;

    @PostMapping("/subscriptions/cancel")
    String cancelSubscription(@RequestBody RequestDTO requestDTO)
            throws StripeException;

    @PostMapping("/subscriptions/list")
    List<Map<String, String>> viewSubscriptions(
            @RequestBody RequestDTO requestDTO) throws StripeException;

    @PostMapping("/subscriptions/trial")
    String newSubscriptionWithTrial(@RequestBody RequestDTO requestDTO)
            throws StripeException;

    @PostMapping("/invoices/list")
    List<Map<String, String>> listInvoices(@RequestBody RequestDTO requestDTO)
            throws StripeException;
}
