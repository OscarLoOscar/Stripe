package com.example.shoppingcart.controller.impl;

import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public interface StripeControllerImpl {
  @PostMapping("checkout/sessions")
  @ResponseStatus(HttpStatus.CREATED)
  public String createPaymentSession(JwtAuthenticationToken jwt);
}
