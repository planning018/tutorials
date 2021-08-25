package com.planning.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试 Redis 消息发送订阅功能
 *
 * @author planning
 * @date 2020/5/25 8:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PubSubTest {

    private static final String TOPIC = "TEST";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testPubSub() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            stringRedisTemplate.convertAndSend(TOPIC, "planning: " + i);
            Thread.sleep(1000L);
        }
    }

}
