package com.example.alfabanktask.services;

import com.example.alfabanktask.dto.GifInfo;
import com.example.alfabanktask.dto.GiphyApiClient;
import com.example.alfabanktask.dto.GiphyApiResponse;
import com.example.alfabanktask.exceptions.EqualsCurrencyRatesException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AppServicesTests {

    @MockBean
    GiphyApiClient client;
    @Autowired
    AppService service;

    GiphyApiResponse response;
    String link;

    @BeforeEach
    public void beforeEach(){
        link = "url";
        GifInfo gifInfo = new GifInfo(link);
        Map<String, GifInfo> images = Map.of("original", gifInfo);
        response = new GiphyApiResponse(new GiphyApiResponse.ResponseData(images));
    }

    @Test
    public void givenPositiveDifference_whenValidClientResponses_returnsGifUrl() {
        double diff = 1;
        when(client.getRandomGif(anyString(), anyString(), anyString())).thenReturn(response);
        String gifLink = service.getGifLink(diff);
        assertThat(gifLink).isEqualTo(link);
    }

    @Test
    public void givenNegativeDifference_whenValidClientResponses_returnsGifUrl() {
        double diff = -1;
        when(client.getRandomGif(anyString(), anyString(), anyString())).thenReturn(response);
        String gifLink = service.getGifLink(diff);
        assertThat(gifLink).isEqualTo(link);
    }

    @Test
    public void givenNoDifference_whenValidClientResponses_throwsEqualsCurrencyRatesException() {
        double diff = 0;
        assertThrows(EqualsCurrencyRatesException.class, () -> service.getGifLink(diff));
    }
}
