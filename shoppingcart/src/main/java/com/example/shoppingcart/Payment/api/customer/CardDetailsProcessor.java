package com.example.shoppingcart.Payment.api.customer;

import java.util.Map;
import org.springframework.stereotype.Service;
import com.example.shoppingcart.Payment.Config.StripeConfig;
import com.example.shoppingcart.Payment.Dto.CreateCardDetailsTokenRequest;
import com.stripe.exception.StripeException;
import com.stripe.model.Token;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardDetailsProcessor {
  private final StripeConfig stripeConfig;

  public String processCardDetails(CreateCardDetailsTokenRequest createCardDetailsTokenRequest){
    StripeConfig.setStripeKey(stripeConfig.publishableKey());

    Map<String,Object> card = createCardDetails(createCardDetailsTokenRequest);
    Token cardDetailsToken;
    try{
       cardDetailsToken = Token.create(Map.of("card",card));
    }catch (StripeException e ){
      throw new CardTokenCreationException();
    }
  }
}
