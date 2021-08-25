package com.planning.redis.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * @author planning
 * @date 2020/5/25 7:53
 */
@Slf4j
public class TestPatternTopicMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        log.info("收到 PatternTopic 消息：");
        log.info("线程编号：{}", Thread.currentThread().getName());
        log.info("message is {}", message);
        log.info("pattern is {}", new String(pattern));
    }
}
