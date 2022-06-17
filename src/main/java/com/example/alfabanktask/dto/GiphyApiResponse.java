package com.example.alfabanktask.dto;

import lombok.Data;

import java.util.HashMap;

@Data
public class GiphyApiResponse {

    public ResponseData data;

    @Data
    static class ResponseData {
        HashMap<String, GifInfo> images;
    }
}
