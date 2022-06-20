package com.example.alfabanktask.dto;

import org.apache.commons.io.IOUtils;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 9091)
public class CurrencyApiClientTests {

    @Autowired
    CurrencyApiClient client;

    @Test
    public void getLatestCurrencyRate_whenValidClient_returnValidResponse() throws Exception {
        stubFor(get(urlPathEqualTo("/latest.json"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(read("/LatestCurrencyRateApiResponse.json"))));

        Map<String, Double> rates = client.getLatestCurrencyRate(anyString(), anyString(), anyString()).getRates();
        assertThat(rates.get("RUB")).isCloseTo(55.75, Offset.offset(0.01));
    }

    @Test
    public void getPastCurrencyRate_whenValidClient_returnValidResponse() throws Exception {
        String date = "2022-06-19";
        String url = String.format("/historical/%s.json", date);
        stubFor(get(urlPathEqualTo(url))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(read("/PastCurrencyRateApiResponse.json"))));

        Map<String, Double> rates = client.getPastCurrencyRate(date, anyString(), anyString(), anyString()).getRates();
        assertThat(rates.get("RUB")).isCloseTo(56.75, Offset.offset(0.01));
    }

    private String read(String location) throws IOException {
        return IOUtils.toString(new ClassPathResource(location).getInputStream());
    }

}
