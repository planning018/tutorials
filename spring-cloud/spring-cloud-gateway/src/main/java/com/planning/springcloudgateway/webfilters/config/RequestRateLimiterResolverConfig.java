package com.planning.springcloudgateway.webfilters.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * @author yxc
 * @date 2021/9/17 10:11 上午
 */
@Configuration
public class RequestRateLimiterResolverConfig {

    @Bean
    KeyResolver userKeyResolver(){
        return exchange -> Mono.just("1");
    }
}
