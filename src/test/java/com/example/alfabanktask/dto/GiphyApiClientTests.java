package com.example.alfabanktask.dto;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 9091)
public class GiphyApiClientTests {

    @Autowired
    GiphyApiClient client;

    @Test
    public void getRandomGifLink_whenValidClient_returnValidResponse() throws Exception {
        stubFor(get(urlPathEqualTo("/random"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(read("/GiphyApiResponse.json"))));

        GifInfo gifInfo = client.getRandomGif(anyString(), anyString(), anyString()).getData().getImages().get("original");
        String originalFormatGifUrl = "https://media1.giphy.com/media/LS33kDnx38ZxWm0X2k/giphy.gif";

        assertThat(gifInfo.getUrl()).contains(originalFormatGifUrl);
    }

    private String read(String location) throws IOException {
        return IOUtils.toString(new ClassPathResource(location).getInputStream());
    }
}
