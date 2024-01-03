package com.example.shoppingcart.infra;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import com.example.shoppingcart.model.FireBaseUserData;

public class JwtUntil {

  public static FireBaseUserData getFireBaseUser(JwtAuthenticationToken jwt) {
    return new FireBaseUserData(jwt);
  }
}
