package com.example.alfabanktask.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
@NoArgsConstructor
public class EqualsCurrencyRatesException extends IllegalStateException{

    public EqualsCurrencyRatesException(String s) {
        super(s);
    }
}
