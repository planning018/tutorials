package com.planning.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.JedisCluster;

/**
 * @author yxc
 * @date 2022/8/17 5:56 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisCrudTest {

    @Autowired
    private JedisCluster jedisCluster;

    @Test
    public void testAdd(){
        jedisCluster.set("emp4", "zhangsan");
    }
}
