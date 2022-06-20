package com.example.alfabanktask.services;

import com.example.alfabanktask.dto.GiphyApiClient;
import com.example.alfabanktask.dto.GiphyApiResponse;
import com.example.alfabanktask.exceptions.EqualsCurrencyRatesException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class AppService {

    @Autowired
    GiphyApiClient client;
    @Autowired
    Environment env;

    public static final String EQUALS_RATES_MSG = "There are no differences were happened between currency rates";
    public static final String PROP_PREFIX = "api.giphy.";

    public String getGifLink(double d) {
        if (d == 0) {
            throw new EqualsCurrencyRatesException(EQUALS_RATES_MSG);
        }
        String tagSign = d > 0 ? "tag.positive" : "tag.negative";
        String tag = env.getProperty(PROP_PREFIX + tagSign);
        String key = env.getProperty(PROP_PREFIX + "api_key");
        String rating = env.getProperty(PROP_PREFIX + "rating");

        GiphyApiResponse response = client.getRandomGif(key, tag, rating);

        String format = env.getProperty(PROP_PREFIX + "image.format");
        return response.getData().getImages().get(format).getUrl();
    }
}
