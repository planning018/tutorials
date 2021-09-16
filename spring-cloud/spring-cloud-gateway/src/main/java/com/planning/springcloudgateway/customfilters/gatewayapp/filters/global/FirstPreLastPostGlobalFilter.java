package com.planning.springcloudgateway.customfilters.gatewayapp.filters.global;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author yxc
 * @date 2021/9/15 4:53 下午
 */
@Component
public class FirstPreLastPostGlobalFilter implements GlobalFilter, Ordered {

    final Logger logger = LoggerFactory.getLogger(FirstPreLastPostGlobalFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 执行前置过滤
        logger.info("First Pre Global Filter executed");
        return chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {
                    // 执行后置过滤
                    logger.info("Last Post Global Filter executed");
                }));
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
