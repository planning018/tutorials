package com.planning.springcloudgateway.customfilters.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author yxc
 * @date 2021/9/16 9:55 上午
 */
@SpringBootTest(classes = ServiceApplication.class)
public class SpringContextTest {

    @Test
    public void whenSpringContextIsBootstrapped_thenNoExceptions() {
        String testStr = "Hello World";
        assertTrue(testStr.contains("Hello World"));
    }
}
