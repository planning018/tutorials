package com.planning.springcloudgateway.customfilters.gatewayapp;

import ch.qos.logback.classic.spi.ILoggingEvent;
import com.planning.springcloudgateway.customfilters.gatewayapp.utils.LoggerListAppender;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author yxc
 * @date 2021/9/16 10:23 上午
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomFiltersLiveTest {

    @LocalServerPort
    private String port;

    @Autowired
    private WebTestClient webClient;

    @BeforeEach
    public void clearLogList(){
        LoggerListAppender.clearEventList();
        webClient = WebTestClient.bindToServer()
                .baseUrl("http://localhost:" + port)
                .build();
    }

    @Test
    public void whenCallServiceThroughGateway_thenAllConfiguredFiltersGetExecuted() {
        ResponseSpec response = webClient.get()
                .uri("/service/resource")
                .exchange();

        response.expectStatus()
                .isOk()
                .expectHeader()
                .doesNotExist("Bael-Custom-Language-Header")
                .expectBody(String.class)
                .isEqualTo("Service Resource");

        assertThat(LoggerListAppender.getEvents())
                // Global Pre Filter
                .haveAtLeastOne(eventContains("Global Pre Filter executed"))
                // Global Post Filter
                .haveAtLeastOne(eventContains("Global Post Filter executed"))
                // Global Pre and Post Filter
                .haveAtLeastOne(eventContains("First Pre Global Filter"))
                .haveAtLeastOne(eventContains("Last Post Global Filter"))
                // Logging Filter Factory
                .haveAtLeastOne(eventContains("Pre Gateway Filter logging: My Custom Message"))
                .haveAtLeastOne(eventContains("Post Gateway Filter logging: My Custom Message"))
                // Modify Request
                .haveAtLeastOne(eventContains("Modify request output - Request contains Accept-Language header:"))
                .haveAtLeastOne(eventConditionExcept("Removed all query params: ", "locale"))
                // Modify Response
                .areNot(eventContains("Added custom header to response"))
                // chain Request
                .haveAtLeastOne(eventContains("Chain Request output - Request contains Accept-Language header:"));
    }

    @Test
    public void givenRequestWithLocaleQueryParam_whenCallServiceThroughGateway_whenAllConfiguredFiltersGetExecuted(){
        ResponseSpec response = webClient.get()
                .uri("/service/resource?locale=en")
                .exchange();

        response.expectStatus()
                .isOk()
                .expectHeader()
                .exists("Bael-Custom-Language-Header")
                .expectBody(String.class)
                .isEqualTo("Service Resource");

        assertThat(LoggerListAppender.getEvents())
                // modify response
                .haveAtLeastOne(eventContains("Added custom header to response"))
                .haveAtLeastOne(eventConditionExcept("Removed all query params: ", "locale"));
    }


    /**
     * This condition will be successful if the event contains a substring
     *
     * @param subString string
     * @return
     */
    private Condition<ILoggingEvent> eventContains(String subString) {
        return new Condition<ILoggingEvent>(
                entry -> (subString == null || (entry.getFormattedMessage() != null && entry.getFormattedMessage().contains(subString))),
                String.format("entry with message '%s'", subString)
        );
    }


    /**
     * This condition will be successful if the event contains a substring, but not another one
     *
     * @param substring
     * @param except
     * @return
     */
    private Condition<ILoggingEvent> eventConditionExcept(String substring, String except){
        return new Condition<ILoggingEvent>(
                entry -> (substring == null ||
                        (entry.getFormattedMessage() != null
                                && entry.getFormattedMessage().contains(substring)
                                && !entry.getFormattedMessage().contains(except))),
                String.format("entry with message '%s'", substring)
        );
    }
}
