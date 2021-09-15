package com.planning.springcloudgateway.customfilters.secondservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * @author yxc
 * @date 2021/9/15 3:24 下午
 */
@SpringBootApplication
@PropertySource("classpath:secondservice-application.properties")
public class SecondServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecondServiceApplication.class, args);
    }
}
