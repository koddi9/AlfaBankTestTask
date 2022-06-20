package com.example.alfabanktask.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EqualsCurrencyRatesException extends RuntimeException {

    public EqualsCurrencyRatesException(String s) {
        super(s);
    }
}
