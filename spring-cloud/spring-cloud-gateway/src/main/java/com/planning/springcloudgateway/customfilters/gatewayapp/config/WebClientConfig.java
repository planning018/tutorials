package com.planning.springcloudgateway.customfilters.gatewayapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author yxc
 * @date 2021/9/16 3:05 下午
 */
@Configuration
public class WebClientConfig {

    @Bean
    public WebClient client(){
        return WebClient.builder().build();
    }
}
