package com.example.alfabanktask.dto;

import com.example.alfabanktask.dto.GiphyApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "${api.giphy.value}", url = "${api.giphy.url}")
public interface GiphyApiClient {

    @RequestMapping(method = RequestMethod.GET, value = "/random")
    ResponseEntity<GiphyApiResponse> getRandomGif(
            @RequestParam(name = "api_key") String apiKey,
            @RequestParam(name = "tag") String tag,
            @RequestParam(name = "rating") String rating);

}
