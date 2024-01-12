package com.example.shoppingcart.stripePayment.service.Impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.example.shoppingcart.infra.enums.Currency;
import com.example.shoppingcart.stripePayment.dto.StripeChargeDto;
import com.example.shoppingcart.stripePayment.dto.StripeSubscriptionDto;
import com.example.shoppingcart.stripePayment.dto.StripeSubscriptionResponse;
import com.example.shoppingcart.stripePayment.dto.StripeTokenDto;
import com.example.shoppingcart.stripePayment.service.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.PaymentMethod;
import com.stripe.model.Subscription;
import com.stripe.model.Token;
import com.stripe.net.RequestOptions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StripeServiceImpl implements StripeService {

  // @Value("${use.secret.key:false}")
  // private boolean useSecretKey;

  @Value("${stripe.api.publicKey}")
  private String stripePublicKey;

  @Value("${stripe.api.secretKey}")
  private String stripeSecretKey;

  @Override
  public StripeTokenDto createCardToken(StripeTokenDto model) {
    try {
      Map<String, Object> card = new HashMap<>();
      card.put("number", model.getCardNumber());
      card.put("exp_month", Integer.parseInt(model.getExpMonth()));
      card.put("exp_year", Integer.parseInt(model.getExpYear()));
      card.put("cvc", model.getCvc());
      Map<String, Object> params = new HashMap<>();
      params.put("card", card);
      Token token = Token.create(params);
      if (token != null && token.getId() != null) {
        model.setSuccess(true);
        model.setToken(token.getId());
      }
      return model;
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  @Override
  public StripeChargeDto charge(StripeChargeDto chargeRequest) {
    try {
      chargeRequest.setSuccess(false);
      Map<String, Object> chargeParams = new HashMap<>();
      chargeParams.put("amount",
          Math.round(Double.parseDouble(chargeRequest.getAmount()) * 100));
      chargeParams.put("currency", Currency.HKD.name());
      chargeParams.put("description", "Payment for id "
          + chargeRequest.getAdditionInfo().getOrDefault("ID_TAG", ""));
      chargeParams.put("source", chargeRequest.getStripeToken());

      Map<String, Object> metaData = new HashMap<>();
      metaData.put("id", chargeRequest.getChargeId());
      metaData.putAll(chargeRequest.getAdditionInfo());
      chargeParams.put("metadata", metaData);

      // Use the appropriate key based on the configuration
      String apiKey = stripeSecretKey;
      // Set up RequestOptions with the API key
      RequestOptions requestOptions =
          RequestOptions.builder().setApiKey(apiKey).build();

      Charge charge = Charge.create(chargeParams, requestOptions);
      chargeRequest.setMessage(charge.getOutcome().getSellerMessage());

      if (charge.getPaid()) {
        chargeRequest.setStripeToken(charge.getId());
        chargeRequest.setSuccess(true);
      }
      return chargeRequest;
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  @Override
  public StripeSubscriptionResponse createSubscription(
      StripeSubscriptionDto subscriptionDto) {
    PaymentMethod paymentMethod = createPaymentMethod(subscriptionDto);

    Customer customer = createCustomer(paymentMethod, subscriptionDto);

    paymentMethod = attachCustomerToPaymentMethod(customer, paymentMethod);

    Subscription subscription =
        createSubscription(subscriptionDto, paymentMethod, customer);
    log.info("subscription :" + subscription);
    return createResponse(subscriptionDto, paymentMethod, customer,
        subscription);
  }

  @Override
  public Subscription cancelSubscription(String subscriptionId) {
    try {
      Subscription retrieve = Subscription.retrieve(subscriptionId);
      return retrieve.cancel();
    } catch (StripeException e) {
      log.error("StripeService (cancel Subscription)" + e);
    }
    return null;
  }

  private StripeSubscriptionResponse createResponse(
      StripeSubscriptionDto subscriptionDto, PaymentMethod paymentMethod,
      Customer customer, Subscription subscription) {
    return StripeSubscriptionResponse.builder()//
        .username(subscriptionDto.getUsername())//
        .stripeCustomerId(customer.getId())//
        .stripePaymentMethodId(paymentMethod.getId())//
        .stripeSubscriptionId(subscription.getId())//
        .build();
  }

  private PaymentMethod createPaymentMethod(
      StripeSubscriptionDto subscriptionDto) {
    try {
      Map<String, Object> card = new HashMap<>();
      card.put("number", subscriptionDto.getCardNumber());
      card.put("exp_month", Integer.parseInt(subscriptionDto.getExpMonth()));
      card.put("exp_year", Integer.parseInt(subscriptionDto.getExpYear()));
      card.put("cvc", subscriptionDto.getCvc());

      Map<String, Object> params = new HashMap<>();
      params.put("type", "card");
      params.put("card", card);

      // Use the appropriate key based on the configuration

      String apiKey = stripePublicKey;
      // Set up RequestOptions with the API key
      RequestOptions requestOptions =
          RequestOptions.builder().setApiKey(apiKey).build();


      return PaymentMethod.create(params, requestOptions);
    } catch (StripeException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  private Customer createCustomer(PaymentMethod paymentMethod,
      StripeSubscriptionDto subscriptionDto) {
    try {
      Map<String, Object> customerMap = new HashMap<>();
      customerMap.put("name", subscriptionDto.getUsername());
      customerMap.put("email", subscriptionDto.getEmail());
      customerMap.put("payment_method", paymentMethod.getId());
      // Use the appropriate key based on the configuration
      String apiKey = stripeSecretKey;
      // Set up RequestOptions with the API key
      RequestOptions requestOptions =
          RequestOptions.builder().setApiKey(apiKey).build();

      return Customer.create(customerMap, requestOptions);
    } catch (StripeException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  private PaymentMethod attachCustomerToPaymentMethod(Customer customer,
      PaymentMethod paymentMethod) {
    // Use the appropriate key based on the configuration
    String apiKey = stripeSecretKey;
    // Set up RequestOptions with the API key
    RequestOptions requestOptions =
        RequestOptions.builder().setApiKey(apiKey).build();

    try {

      paymentMethod = com.stripe.model.PaymentMethod
          .retrieve(paymentMethod.getId(), requestOptions);

      Map<String, Object> params = new HashMap<>();
      params.put("customer", customer.getId());

      paymentMethod = paymentMethod.attach(params, requestOptions);
      return paymentMethod;


    } catch (StripeException e) {
      log.error("StripeService (attachCustomerToPaymentMethod)", e);
      throw new RuntimeException(e.getMessage());
    }

  }

  private Subscription createSubscription(StripeSubscriptionDto subscriptionDto,
      PaymentMethod paymentMethod, Customer customer) {
    try {
      List<Object> items = new ArrayList<>();
      Map<String, Object> items1 = new HashMap<>();
      items1.put("price", subscriptionDto.getPriceId());
      items1.put("quantity", subscriptionDto.getNumberOfLicense());
      items.add(items1);

      Map<String, Object> params = new HashMap<>();
      params.put("customer", customer.getId());
      params.put("default_payment_method", paymentMethod.getId());
      params.put("items", items);
      // Use the appropriate key based on the configuration
      String apiKey = stripeSecretKey;
      // Set up RequestOptions with the API key
      RequestOptions requestOptions =
          RequestOptions.builder().setApiKey(apiKey).build();


      return Subscription.create(params, requestOptions);

    } catch (StripeException e) {
      throw new RuntimeException(e.getMessage());
    }
  }
}
