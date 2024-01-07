package com.example.shoppingcart.Payment.Dto;

import java.math.BigDecimal;
import java.util.Set;
import com.example.shoppingcart.Payment.enums.PaymentStatus;
import com.example.shoppingcart.model.CartItemData;
import lombok.Builder;

@Builder
public record ProcessedPaymentDetailsDto(
        Long paymentId,//
        BigDecimal itemsTotalPrice,//
        String paymentIntentId,//
        Set<CartItemData> items,//
        PaymentStatus status,//
        String description//
) {
}