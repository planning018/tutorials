package com.planning.spring.cloud.sentinel.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author yxc
 * @date 2021/12/16 2:00 下午
 */
@Slf4j
@Service
public class GreetingService {

    @SentinelResource(value = "greeting", fallback = "getGreetingFallback")
    public String getGreeting() {
        int randomNum = ThreadLocalRandom.current().nextInt(100);
        System.out.println("randomNum is " + randomNum);
        if (randomNum < 90) {
            throw new RuntimeException("test sentinel fallback exception");
        }
        return "Hello World";
    }

    public String getGreetingFallback(Throwable e) {
        e.printStackTrace();
        return "Bye world";
    }

    public String blockHandlerGreeting(BlockException e) {
        System.out.println("enter blockHandlerGreeting");
        e.printStackTrace();
        return "blockHandlerGreeting";
    }

}
