package com.planning.redis.controller;

import com.alibaba.fastjson.JSON;
import com.planning.redis.bean.UserCacheBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author yxc
 * @date 2022/8/18 5:26 下午
 */
@Component
public class RedisCrudViaJedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void insertUser(UserCacheBean request) {
        // RedisTemplate set object
        UserCacheBean userCacheBean = UserCacheBean.builder()
                .id(request.getId())
                .name(request.getName())
                .gender(request.getGender())
                .build();
        String key = String.format("User:%d", userCacheBean.getId());
        stringRedisTemplate.opsForValue().set(key, JSON.toJSONString(userCacheBean));
    }
}
