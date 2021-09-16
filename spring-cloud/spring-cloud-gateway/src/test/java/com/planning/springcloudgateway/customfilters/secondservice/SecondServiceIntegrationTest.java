package com.planning.springcloudgateway.customfilters.secondservice;

import com.planning.springcloudgateway.customfilters.secondservice.web.SecondServiceRestController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * @author yxc
 * @date 2021/9/16 10:18 上午
 */
@WebFluxTest(SecondServiceRestController.class)
public class SecondServiceIntegrationTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    public void whenResourceLanguageEndpointCalled_thenRetrievesSpanishLanguageString() throws Exception {
        this.webClient.get()
                .uri("/resource/language")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(String.class)
                .isEqualTo("es");
    }

}
