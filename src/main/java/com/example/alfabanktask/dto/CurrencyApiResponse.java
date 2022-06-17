package com.example.alfabanktask.dto;

import lombok.Data;
import lombok.Getter;

import java.util.HashMap;

@Data
public class CurrencyApiResponse {

    private String base;
    private HashMap<String,Double> rates;

}