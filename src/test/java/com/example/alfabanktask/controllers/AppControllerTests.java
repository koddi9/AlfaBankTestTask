package com.example.alfabanktask.controllers;

import com.example.alfabanktask.services.AppService;
import com.example.alfabanktask.services.OerCurrencyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class AppControllerTests {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private AppService appService;
    @MockBean
    private OerCurrencyService oerCurrencyService;

    @Test
    public void givenTargetValue_whenGetDifference_andGetRandomGif_thenReturnGifUrl() throws Exception {
        double d1 = 62;
        double d2 = 61;
        double diff = Double.compare(d1, d2);
        Map<String, Double> currencyRatesMap = Map.of("current", d1, "past", d2, "difference", diff);

        when(oerCurrencyService.getCurrencyRates(anyString())).thenReturn(currencyRatesMap);
        when(appService.getGifLink(anyDouble())).thenReturn(anyString());
        mvc.perform(get("/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenTargetValue_whenServiceThrowsRuntimeException_returnsInternalServerError() throws Exception {
        doThrow(RuntimeException.class).when(oerCurrencyService).getCurrencyRates(anyString());
        mvc.perform(get("/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void givenPostMethodRequest_whenUnsupportedHttpMethod_returnsMethodNotAllowed() throws Exception {
        mvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }
}
