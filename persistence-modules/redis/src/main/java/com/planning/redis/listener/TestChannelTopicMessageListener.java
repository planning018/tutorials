package com.planning.redis.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * @author planning
 * @date 2020/5/25 7:45
 */
@Slf4j
public class TestChannelTopicMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        log.info("收到 ChannelTopic 消息：");
        log.info("线程编号：{}", Thread.currentThread().getName());
        log.info("message is {}", message);
        log.info("pattern is {}", new String(pattern));
    }
}
