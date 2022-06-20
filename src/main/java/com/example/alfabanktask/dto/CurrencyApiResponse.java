package com.example.alfabanktask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class CurrencyApiResponse {

    private String base;
    private Map<String, Double> rates;

}