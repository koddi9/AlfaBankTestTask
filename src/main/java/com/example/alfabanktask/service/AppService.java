package com.example.alfabanktask.service;

import com.example.alfabanktask.exception.EqualsCurrencyRatesException;
import com.example.alfabanktask.dto.GiphyApiClient;
import com.example.alfabanktask.dto.GiphyApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class AppService {

    @Autowired
    GiphyApiClient client;
    @Autowired
    Environment env;

    public static final String EQUALS_RATES_MSG = "Im sorry Vito, there are no differences were happened";
    public static final String PROP_PREFIX = "api.giphy.";
//    @Value(value = "${api.giphy.tag.positive}")
//    private String POSITIVE_TAG;
//    @Value(value = "${api.giphy.tag.negative}")
//    private String NEGATIVE_TAG;

    public String getGifLink(double d) {
//        throw new EqualsCurrencyRatesException("Im sorry Vito, there are no differences were happened");

//        Optional<GiphyApiResponse> response = Optional.empty();
        if (d==0) {
            throw new EqualsCurrencyRatesException(EQUALS_RATES_MSG);
        }
        String tagSign = d > 0 ? "tag.positive" : "tag.negative";
        String tag = env.getProperty(PROP_PREFIX + tagSign);
        String key = env.getProperty(PROP_PREFIX + "api_key");
        String rating = env.getProperty(PROP_PREFIX + "rating");
//            String tag = d > 0 ? env.getProperty(PROP_PREFIX + "tag.positive") : env.getProperty(PROP_PREFIX + "tag.negative");
        GiphyApiResponse response = client.getRandomGif(key, tag, rating).getBody();

        String format = env.getProperty(PROP_PREFIX + "image.format");
        return response.getData().getImages().get(format).getUrl();
    }
}
