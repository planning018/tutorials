package com.planning.springcloudgateway.customfilters.gatewayapp.filters.global;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * @author yxc
 * @date 2021/9/15 3:16 下午
 */
@Configuration
public class LoggingGlobalFiltersConfigurations {

    final Logger logger = LoggerFactory.getLogger(LoggingGlobalFiltersConfigurations.class);

    @Bean
    public GlobalFilter postGlobalFilter(){
        return (exchange, chain) -> {
            return chain.filter(exchange)
                    .then(Mono.fromRunnable(() -> {
                        logger.info("Global Post Filter executed");
                    }));
        };
    }
}
