package com.planning.redis.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

/**
 * @author yxc
 * @date 2022/8/17 5:48 下午
 */
//@Configuration
public class RedisClusterConfig {

//    @Bean
    public JedisCluster jedisCluster() {
        GenericObjectPoolConfig<Jedis> poolConfig = new GenericObjectPoolConfig<Jedis>();
        poolConfig.setMaxTotal(1000);
        poolConfig.setMaxIdle(400);
        poolConfig.setMinIdle(400);
        poolConfig.setMaxWaitMillis(2000);
        return new JedisCluster(new HostAndPort("xxxx", 30069),
                2000, 1000, 100, "xxxx", poolConfig);
    }


}
