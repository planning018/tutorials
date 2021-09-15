package com.planning.springcloudgateway.customfilters.gatewayapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * @author yxc
 * @date 2021/9/15 3:11 下午
 */
@SpringBootApplication
@PropertySource("classpath:customfilters-global-application.properties")
public class CustomFiltersGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomFiltersGatewayApplication.class, args);
    }
}
