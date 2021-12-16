package com.planning.spring.cloud.sentinel.controller;

import com.planning.spring.cloud.sentinel.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yxc
 * @date 2021/12/16 1:58 下午
 */
@RestController
public class GreetingController {

    @Autowired
    private GreetingService greetingService;

    @GetMapping("/hello")
    public String getGreeting(){
        return greetingService.getGreeting();
    }
}
