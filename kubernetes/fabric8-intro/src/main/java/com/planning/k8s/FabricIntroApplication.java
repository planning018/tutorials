package com.planning.k8s;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yxc
 * @date 2022/8/30 5:14 下午
 */
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@ComponentScan(basePackages = {"com.planning.k8s.*"})
public class FabricIntroApplication {

    public static void main(String[] args) {
        SpringApplication.run(FabricIntroApplication.class, args);
    }
}
