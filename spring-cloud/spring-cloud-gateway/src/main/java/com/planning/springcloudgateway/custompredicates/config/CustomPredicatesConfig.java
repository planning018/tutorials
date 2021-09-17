package com.planning.springcloudgateway.custompredicates.config;

import com.planning.springcloudgateway.custompredicates.factories.GoldenCustomerRoutePredicateFactory;
import com.planning.springcloudgateway.custompredicates.service.GoldenCustomerService;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yxc
 * @date 2021/9/16 3:29 下午
 */
@Configuration
public class CustomPredicatesConfig {

    @Bean
    public GoldenCustomerRoutePredicateFactory goldenCustomer(GoldenCustomerService goldenCustomerService){
        return new GoldenCustomerRoutePredicateFactory(goldenCustomerService);
    }

    // @Bean
    public RouteLocator routes(RouteLocatorBuilder builder, GoldenCustomerRoutePredicateFactory gf){
        return builder.routes()
                .route("dsl_golden_route", r -> r.path("/dsl_api/**")
                    .filters(f -> f.stripPrefix(1))
                    .uri("http://www.google.com")
                    .predicate(gf.apply(new GoldenCustomerRoutePredicateFactory.Config(true, "customerId"))))
                .route("dsl_common_route", r -> r.path("/dsl_api/**")
                    .filters(f -> f.stripPrefix(1))
                    .uri("http://www.baidu.com")
                    .predicate(gf.apply(new GoldenCustomerRoutePredicateFactory.Config(false, "customerId"))))
                .build();
    }
}
