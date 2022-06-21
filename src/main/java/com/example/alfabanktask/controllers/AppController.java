package com.example.alfabanktask.controllers;

import com.example.alfabanktask.services.AppService;
import com.example.alfabanktask.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping
public class AppController {

    @Autowired
    CurrencyService oerCurrencyService;
    @Autowired
    AppService appService;

    @GetMapping
    public String getGif(@RequestParam(defaultValue = "${api.currency.target.default}", required = false) String target, Model model) {

        Map<String, Double> currencyRates = oerCurrencyService.getCurrencyRates(target);
        model.addAllAttributes(currencyRates);
        String link = appService.getGifLink(currencyRates.get("difference"));

        model.addAttribute("gifLink", link);
        return "main";
    }
}
