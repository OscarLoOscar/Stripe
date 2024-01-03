package com.example.shoppingcart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.shoppingcart.infra.JwtUntil;
import com.example.shoppingcart.model.FireBaseUserData;
import com.example.shoppingcart.model.UserData;
import com.example.shoppingcart.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  UserService userService;

  @GetMapping("/me/details")
  public UserData getMyUserDetails(JwtAuthenticationToken jwtToken) {
    FireBaseUserData fireBaseUserData = JwtUntil.getFireBaseUser(jwtToken);
    userService.getEntityByFireBaseUserData(fireBaseUserData);
    return UserData.builder()//
        .userId(userService.findUserByEmail(fireBaseUserData.getEmail())
            .getUserId())//
        .fireBaseUid(fireBaseUserData.getFirebaseUid())//
        .email(fireBaseUserData.getEmail())//
        .build();

  }
}
