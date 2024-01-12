package com.example.shoppingcart.exception.payment;

import lombok.Getter;

@Getter
public class PaymentEventParsingException extends RuntimeException {

    private final String eventType;

    public PaymentEventParsingException(String eventType) {
        super(String.format("PaymentEvent = '%s' cannot be parsed.", eventType));
        this.eventType = eventType;
    }
}
