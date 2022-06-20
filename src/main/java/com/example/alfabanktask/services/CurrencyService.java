package com.example.alfabanktask.services;

import java.util.Map;

public interface CurrencyService {

    /**
     * @param target value that is considered relative to the "base" value (dollar)
     * @return Map<String,Double> which contains the corresponding currency rate values under the keys "current",
     * "past" and "difference"
     */
    Map<String, Double> getCurrencyRates(String target);
}
