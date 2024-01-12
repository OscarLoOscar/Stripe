package com.example.shoppingcart.exception.payment;

import com.example.shoppingcart.exception.setting.Code;
import lombok.Getter;

@Getter
public class UnexpectedPaymentStatusException extends RuntimeException {

    private final Code paymentStatus;

    public UnexpectedPaymentStatusException(final Code paymentStatus) {
        super(String.format("Payment status = %s is unexpected.", paymentStatus));
        this.paymentStatus = paymentStatus;
    }
}
