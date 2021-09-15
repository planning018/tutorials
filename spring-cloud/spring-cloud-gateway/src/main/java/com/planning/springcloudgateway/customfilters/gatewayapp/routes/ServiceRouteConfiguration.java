package com.planning.springcloudgateway.customfilters.gatewayapp.routes;

import com.planning.springcloudgateway.customfilters.gatewayapp.filters.factories.LoggingGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 这里做一个案例，可以将路径配置以代码形式指定
 *
 * @author yxc
 * @date 2021/9/15 5:20 下午
 */
//@Configuration
public class ServiceRouteConfiguration {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder, LoggingGatewayFilterFactory loggingFactory){
        return builder.routes()
                .route("service_route_java_config", r -> r.path("/service/**")
                        .filters(f -> f.rewritePath("/service(?<segment>/?.*)", "$\\{segment}")
                            .filter(loggingFactory.apply(new LoggingGatewayFilterFactory.Config("My Custom Message", true, true))))
                        .uri("http://localhost:8081"))
                .build();
    }
}
