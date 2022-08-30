package com.planning.redis.controller;

import com.planning.redis.bean.UserCacheBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yxc
 * @date 2022/8/18 5:27 下午
 */
@RestController
public class RedisCrudController {

    @Autowired
    private RedisCrudViaJedisService jedisService;

    @PostMapping("/user")
    public void insertDataViaJedis(@RequestBody UserCacheBean request){
        jedisService.insertUser(request);
    }
}
