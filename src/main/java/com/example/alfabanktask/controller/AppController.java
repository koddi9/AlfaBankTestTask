package com.example.alfabanktask.controller;

import com.example.alfabanktask.service.AppService;
import com.example.alfabanktask.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping()
public class AppController {

    @Autowired
    CurrencyService currencyService;
    @Autowired
    AppService appService;


//    @GetMapping
//    public ResponseEntity<Double> getLatestCurrencyRate(@RequestParam(defaultValue = "RUB", required = false) String target) {
//        double d = currencyService.getRatesDifference(target);
//        System.out.println(d);
//        return ResponseEntity.ok(d);
//    }

    @GetMapping
    public String getGif(@RequestParam(defaultValue = "RUB", required = false) String target, Model model) {

//        try {

        Map<String, Double> currencyRates = currencyService.getCurrencyRates(target);
        model.addAllAttributes(currencyRates);
        String link = appService.getGifLink(currencyRates.get("difference"));

        model.addAttribute("gifLink", link);
//        }
//        catch (Exception e) {
//           return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(MediaType.IMAGE_GIF).body("https://media1.giphy.com/media/la6Ne7z15BXs4/giphy.gif");
//            throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, e.getMessage());
//            throw e;
//        }


//        String link = "https://media1.giphy.com/media/la6Ne7z15BXs4/giphy.gif";
//        String link = "https://media0.giphy.com/media/uuaVNbh5LhhOX8f964/giphy.gif?cid=8f218561fabc5575d58a0b1949289e722d80365a05f3392a&rid=giphy.gif&ct=g";

        return "main";
//        return ResponseEntity.ok().build();
    }
}
