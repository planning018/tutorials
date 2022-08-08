package com.planning.springcloudgateway.introduction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * @author yxc
 * @date 2021/9/15 10:55 上午
 */
@SpringBootApplication
@PropertySource("classpath:introduction-application.properties")
public class IntroductionGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntroductionGatewayApplication.class, args);
    }
}
