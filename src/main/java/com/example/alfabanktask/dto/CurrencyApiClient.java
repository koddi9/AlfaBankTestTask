package com.example.alfabanktask.dto;

import com.example.alfabanktask.dto.CurrencyApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "${api.currency.value}", url = "${api.currency.url}")
public interface CurrencyApiClient {

    @RequestMapping(method = RequestMethod.GET,value = "/latest.json")
    ResponseEntity<CurrencyApiResponse> getLatestCurrencyRate(
            @RequestParam("app_id") String appId,
            @RequestParam(name = "base") String base,
            @RequestParam(name = "symbols") String target);

    @RequestMapping(method = RequestMethod.GET,value = "/historical/{date}.json")
    ResponseEntity<CurrencyApiResponse> getPastCurrencyRate(@PathVariable(name = "date") String date,
                                                            @RequestParam("app_id") String appId,
                                                            @RequestParam(name = "base") String base,
                                                            @RequestParam(name = "symbols") String target);


}
