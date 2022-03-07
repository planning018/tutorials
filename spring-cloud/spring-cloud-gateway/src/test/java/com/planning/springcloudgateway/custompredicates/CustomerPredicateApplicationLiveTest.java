package com.planning.springcloudgateway.custompredicates;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author yxc
 * @date 2021/9/16 4:29 下午
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("customroutes")
public class CustomerPredicateApplicationLiveTest {

    @LocalServerPort
    private String serverPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void givenNormalCustomer_whenCallHeadersApi_thenResponseForNormalCustomer() throws Exception {
        String url = "http://localhost:" + serverPort + "/api/headers";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        JSONObject json = new JSONObject(response.getBody());
        JSONObject headers = json.getJSONObject("headers");
        assertThat(headers.getString("Golden-Customer")).isEqualTo("false");
    }

    @Test
    public void givenGoldenCustomer_whenCallHeadersApi_thenResponseForGoldenCustomer() throws Exception {
        String url = "http://localhost:" + serverPort + "/api/headers";
        RequestEntity<Void> request = RequestEntity
                .get(URI.create(url))
                .header("Cookie","customerId=ymm123")
                .build();

        ResponseEntity<String> response = restTemplate.exchange(request, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        JSONObject json = new JSONObject(response.getBody());
        JSONObject headers = json.getJSONObject("headers");
        assertThat(headers.getString("Golden-Customer")).isEqualTo("true");
    }

}
