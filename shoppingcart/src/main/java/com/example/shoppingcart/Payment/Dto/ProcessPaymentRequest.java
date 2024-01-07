package com.example.shoppingcart.Payment.Dto;

import java.util.UUID;
import jakarta.validation.constraints.NotBlank;

public record ProcessPaymentRequest(//
    @NotBlank(
        message = "CardInfoToken is the mandatory attribute") String cardInfoToken,

    @NotBlank(
        message = "CustomerId is the mandatory attribute") UUID customerId) {

}
