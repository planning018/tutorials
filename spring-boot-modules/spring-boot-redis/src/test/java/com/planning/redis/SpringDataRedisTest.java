package com.planning.redis;

import com.alibaba.fastjson.JSON;
import com.planning.redis.bean.UserCacheBean;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * 数据写入读取测试
 *
 * @author yxc
 * @since 2020-05-23 16:00
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataRedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testStringSetKey() {
        // StringRedisTemplate set value
        stringRedisTemplate.opsForValue().set("yxc", "20220817", 2, TimeUnit.MINUTES);
    }

    @Test
    public void testStringSetKey02() {
        // RedisTemplate set value
        redisTemplate.opsForValue().set("yxc-02", "keep-learning-20200523-2");
    }

    @Test
    public void testSetAdd() {
        /*
         * StringRedisTemplate set 集合 (set)
         * SMEMBERS yxc_description 命令查看 set 集合
         */
        stringRedisTemplate.opsForSet().add("yxc_description", "lazy", "stupid");
    }

    @Test
    public void testStringSetKeyUserCache() {
        // RedisTemplate set object
        UserCacheBean userCacheBean = UserCacheBean.builder()
                .id(1)        // 用户 id
                .name("yxc")  // 姓名
                .gender(1)    // 男
                .build();
        String key = String.format("User:%d", userCacheBean.getId());
        redisTemplate.opsForValue().set(key, userCacheBean);
    }

    @Test
    public void testStringGetKeyUserCache() {
        // RedisTemplate get object
        String key = String.format("User:%d", 1);
        Object value = redisTemplate.opsForValue().get(key);
        System.out.println(JSON.toJSONString(value));
    }

    @Test
    public void testSetDiffType() {
        // List
        ArrayList<String> strList = Lists.newArrayList("1", "2", "3");
        String key = String.format("list-%d", 1);
        redisTemplate.opsForValue().set(key, strList);
    }

    @Test
    public void testGetDiffType() {
        System.out.println(JSON.toJSONString(redisTemplate.opsForValue().get("list-1")));
    }


}