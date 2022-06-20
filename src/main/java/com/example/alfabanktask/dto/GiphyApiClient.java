package com.example.alfabanktask.dto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "${api.giphy.value}", url = "${api.giphy.url}")
public interface GiphyApiClient {

    @RequestMapping(method = RequestMethod.GET, value = "/random")
    GiphyApiResponse getRandomGif(
            @RequestParam(name = "api_key") String apiKey,
            @RequestParam(name = "tag") String tag,
            @RequestParam(name = "rating") String rating);

}
