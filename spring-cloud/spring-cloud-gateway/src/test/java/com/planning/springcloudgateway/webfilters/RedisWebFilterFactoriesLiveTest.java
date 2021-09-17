package com.planning.springcloudgateway.webfilters;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import redis.embedded.RedisServer;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author yxc
 * @date 2021/9/17 3:11 下午
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("webfilters")
@TestConfiguration
public class RedisWebFilterFactoriesLiveTest {

    private static final Logger logger = LoggerFactory.getLogger(RedisWebFilterFactoriesLiveTest.class);

    private RedisServer redisServer;

    public RedisWebFilterFactoriesLiveTest() {
    }

    @Before
    public void postConstruct(){
        this.redisServer = new RedisServer(6379);
        redisServer.start();
    }

    @LocalServerPort
    private String port;

    @Autowired
    private TestRestTemplate restTemplate;

    //@Test
    @RepeatedTest(25)
    public void whenCallRedisGetThroughGateway_thenOkStatusOrIsReceived(){
        String url = "http://localhost:" + port + "/redis/get";

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        // assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        logger.info("Received: status -> {}, reason -> {}, remain -> {}",
                response.getStatusCodeValue(),
                response.getStatusCode().getReasonPhrase(),
                response.getHeaders().get("X-RateLimit-Remaining"));
    }

    @After
    public void preDestroy(){
        redisServer.stop();
    }



}
