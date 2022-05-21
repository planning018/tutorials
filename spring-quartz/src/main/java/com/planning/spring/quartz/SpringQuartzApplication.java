package com.planning.spring.quartz;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author yxc
 * @date 2022/5/10 3:23 下午
 */
@SpringBootApplication
public class SpringQuartzApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringQuartzApplication.class).run(args);
    }
}
