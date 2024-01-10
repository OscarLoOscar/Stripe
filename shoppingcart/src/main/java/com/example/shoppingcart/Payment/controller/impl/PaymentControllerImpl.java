package com.example.shoppingcart.payment.controller.impl;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.shoppingcart.payment.model.RequestDTO;
import com.stripe.exception.StripeException;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
@RestController
@RequestMapping("/stripe")
public interface PaymentControllerImpl {

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
