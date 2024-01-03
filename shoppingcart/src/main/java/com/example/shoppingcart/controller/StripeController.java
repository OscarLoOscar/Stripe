package com.example.shoppingcart.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import com.example.shoppingcart.controller.impl.StripeControllerImpl;
import com.example.shoppingcart.entity.UserEntity;
import com.example.shoppingcart.infra.JwtUntil;
import com.example.shoppingcart.model.FireBaseUserData;
import com.example.shoppingcart.services.UserService;
import com.example.shoppingcart.services.impl.StripeServiceImpl;

public class StripeController implements StripeControllerImpl {

  private final StripeServiceImpl stripeServiceImpl;
  private final UserService userService;

  @Autowired
  public StripeController(StripeServiceImpl stripeServiceImpl,
      UserService userService) {
    this.stripeServiceImpl = stripeServiceImpl;
    this.userService = userService;
  }

  @Override
  public String createPaymentSession(JwtAuthenticationToken jwt) {
    FireBaseUserData user = JwtUntil.getFireBaseUser(jwt);
    Optional<UserEntity> userEntity =
        Optional.ofNullable(userService.getEntityByFireBaseUserData(user));
    Long userId = userEntity.get().getUserId();

    if (userEntity.isEmpty()) 
      return "";
    

  }
}
