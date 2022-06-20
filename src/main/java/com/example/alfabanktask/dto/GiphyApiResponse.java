package com.example.alfabanktask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GiphyApiResponse {

    public ResponseData data;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResponseData {
        Map<String, GifInfo> images;
    }
}
