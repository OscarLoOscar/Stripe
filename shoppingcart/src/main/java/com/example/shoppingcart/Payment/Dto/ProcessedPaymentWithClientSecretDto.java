package com.example.shoppingcart.Payment.Dto;

import lombok.Builder;

@Builder
public record ProcessedPaymentWithClientSecretDto(Long paymentId,
    String clientSecret) {

}
