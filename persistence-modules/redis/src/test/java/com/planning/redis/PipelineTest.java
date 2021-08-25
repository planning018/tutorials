package com.planning.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 管道 pipeLine 批量操作
 *
 * @author yxc
 * @since 2020-05-23 18:19
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class PipelineTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testPipe() {
        List<Object> objectList = stringRedisTemplate.executePipelined(new RedisCallback<Object>() {

            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {

                // set 写入
                for (int i = 0; i < 3; i++) {
                    redisConnection.set(String.format("yxc-pipeline:%d", i).getBytes(), "handsome".getBytes());
                }

                // get
                for (int i = 0; i < 3; i++) {
                    redisConnection.get(String.format("yxc-pipeline:%d", i).getBytes());
                }

                return null;
            }
        });

        System.out.println(objectList);
    }


}