package com.planning.springcloudgateway.customfilters.secondservice;

import com.planning.springcloudgateway.customfilters.service.ServiceApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author yxc
 * @date 2021/9/16 9:55 上午
 */
@SpringBootTest(classes = SecondServiceApplication.class)
public class SpringContextTest {

    @Test
    public void whenSpringContextIsBootstrapped_thenNoExceptions() {

    }
}
