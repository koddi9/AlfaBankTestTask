package com.example.alfabanktask.services;

import com.example.alfabanktask.dto.CurrencyApiClient;
import com.example.alfabanktask.dto.CurrencyApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
public class OerCurrencyService implements CurrencyService {

    @Autowired
    CurrencyApiClient client;

    @Value("${api.currency.base:USD}")
    private String base;
    @Value("${api.currency.app_id}")
    private String appId;

    private double getLatestCurrencyRate(String target) {
        CurrencyApiResponse currencyApiResponse = client.getLatestCurrencyRate(appId, base, target);
        Map<String, Double> rates = currencyApiResponse.getRates();

        return rates.get(target);
    }

    private double getPastCurrencyRate(String target) {
        String date = LocalDate.now().minusDays(1).toString();

        CurrencyApiResponse currencyApiResponse = client.getPastCurrencyRate(date, appId, base, target);
        Map<String, Double> rates = currencyApiResponse.getRates();

        return rates.get(target);
    }

    public Map<String, Double> getCurrencyRates(String target) {
        double d1 = getLatestCurrencyRate(target);
        double d2 = getPastCurrencyRate(target);
        double d3 = Double.compare(d1, d2);
        System.out.println("Current rate: " + d1 + "\nPast rate: " + d2 + "\nDifference: " + d3);
        return Map.of("current", d1, "past", d2, "difference", d3);
    }
}
