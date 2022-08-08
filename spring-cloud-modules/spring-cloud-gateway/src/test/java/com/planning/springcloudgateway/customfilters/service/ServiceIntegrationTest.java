package com.planning.springcloudgateway.customfilters.service;

import com.planning.springcloudgateway.customfilters.service.web.ServiceRestController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * @author yxc
 * @date 2021/9/16 9:59 上午
 */
@WebFluxTest(ServiceRestController.class)
public class ServiceIntegrationTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    public void whenResourceEndpointCalled_thenRetrievesResourceStringWithContentLanguageHeader() throws Exception {
        this.webClient.get()
                .uri("/service/resource")
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .valueEquals(HttpHeaders.CONTENT_LANGUAGE, "en")
                .expectBody(String.class)
                .isEqualTo("Service Resource");
    }
}
