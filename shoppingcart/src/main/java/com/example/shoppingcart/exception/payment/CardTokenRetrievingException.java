package com.example.shoppingcart.exception.payment;

import lombok.Getter;

@Getter
public class CardTokenRetrievingException extends RuntimeException {

    private final String cardDetailsTokenId;

    public CardTokenRetrievingException(String cardDetailsTokenId) {
        super(String.format("Cannot retrieve stripe card token by cardDetailsTokenId = %s.", cardDetailsTokenId));
        this.cardDetailsTokenId = cardDetailsTokenId;
    }
}
