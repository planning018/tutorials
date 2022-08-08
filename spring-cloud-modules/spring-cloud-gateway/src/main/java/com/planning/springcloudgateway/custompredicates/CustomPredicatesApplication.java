package com.planning.springcloudgateway.custompredicates;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author yxc
 * @date 2021/9/16 2:54 下午
 */
@SpringBootApplication
public class CustomPredicatesApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(CustomPredicatesApplication.class)
                .profiles("customroutes")
                .run(args);
    }
}
