package com.planning.redis.config;

import com.planning.redis.listener.TestPatternTopicMessageListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * Redis 配置
 *
 * @author yxc
 * @since 2020-05-23 15:43
 **/
@Configuration
public class RedisConfiguration {

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        // 创建 RedisTemplate 对象
        RedisTemplate<String, Object> template = new RedisTemplate<>();

        // 设置开启事务支持
        template.setEnableTransactionSupport(true);

        // 设置 RedisConnection 工厂。它就是实现多种 Java Redis 客户端接入的秘密工厂（核心）
        template.setConnectionFactory(jedisConnectionFactory());

        // 使用 String 序列化方式，序列化 key
        template.setKeySerializer(RedisSerializer.string());

        // 使用 JSON 序列化方式（库是 Jackson），序列化 value
        template.setValueSerializer(RedisSerializer.json());
        return template;
    }

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
        jedisConFactory.setHostName("xxx");
        jedisConFactory.setPort(12345);
        jedisConFactory.setPassword("xxxx");
        jedisConFactory.setTimeout(5000);
        return jedisConFactory;
    }

    /**
     * PUB / SUB
     *
     * @param factory
     * @return
     */
    @Bean
    public RedisMessageListenerContainer listenerContainer(RedisConnectionFactory factory) {
        // 创建 RedisMessageListenerContainer 对象
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();

        // 设置 RedisConnection 工厂
        container.setConnectionFactory(factory);

        // 添加监听器
        // container.addMessageListener(new TestChannelTopicMessageListener(), new ChannelTopic("TEST"));
        container.addMessageListener(new TestPatternTopicMessageListener(), new ChannelTopic("TEST"));
        return container;
    }
}