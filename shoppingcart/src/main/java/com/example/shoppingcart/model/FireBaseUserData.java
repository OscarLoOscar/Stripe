package com.example.shoppingcart.model;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FireBaseUserData {
  private String firebaseUid;
  private String email;

  public FireBaseUserData(JwtAuthenticationToken jwt) {
    this.firebaseUid = (String) jwt.getTokenAttributes().get("user_id");//jwt 中的column name
    this.email = (String) jwt.getTokenAttributes().get("email");
  }

}
