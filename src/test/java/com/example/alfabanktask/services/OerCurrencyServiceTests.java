package com.example.alfabanktask.services;

import com.example.alfabanktask.dto.CurrencyApiClient;
import com.example.alfabanktask.dto.CurrencyApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class OerCurrencyServiceTests {

    @MockBean
    CurrencyApiClient client;
    @Autowired
    OerCurrencyService service;

    @Test
    public void getCurrencyRates_whenValidClientResponses_returnMapOfCurrencies() throws Exception {
        String target = "target";
        String base = "base";
        double latestRate = 61;
        double pastRate = 62;

        CurrencyApiResponse latestRateResponse = new CurrencyApiResponse(base, Map.of(target, latestRate));
        when(client.getLatestCurrencyRate(anyString(), anyString(), anyString())).thenReturn(latestRateResponse);
        CurrencyApiResponse pastRateResponse = new CurrencyApiResponse(base, Map.of(target, pastRate));
        when(client.getPastCurrencyRate(any(), anyString(), anyString(), anyString())).thenReturn(pastRateResponse);

        Map<String, Double> rates = service.getCurrencyRates(target);

        assertThat(rates.get("current")).isEqualTo(latestRate);
        assertThat(rates.get("past")).isEqualTo(pastRate);
        assertThat(rates.get("difference")).isEqualTo(
                Double.compare(latestRate,pastRate));
    }
}
