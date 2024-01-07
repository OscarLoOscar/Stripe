package com.example.shoppingcart.Payment.exception;
import com.example.shoppingcart.Payment.enums.PaymentStatus;
import lombok.Getter;

@Getter
public class UnexpectedPaymentStatusException extends RuntimeException {

    private final PaymentStatus paymentStatus;

    public UnexpectedPaymentStatusException(final PaymentStatus paymentStatus) {
        super(String.format("Payment status = %s is unexpected.", paymentStatus));
        this.paymentStatus = paymentStatus;
    }
}