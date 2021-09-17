package com.planning.springcloudgateway.webfilters;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author yxc
 * @date 2021/9/17 9:47 上午
 */
@SpringBootApplication
public class WebFilterGatewayApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(WebFilterGatewayApplication.class)
                .profiles("webfilters")
                .run(args);
    }
}
