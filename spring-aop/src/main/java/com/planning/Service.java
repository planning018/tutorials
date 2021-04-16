package com.planning;

import org.springframework.stereotype.Component;

/**
 * @author yxc
 * @date 2021/4/16 15:34
 */
@Component
public class Service {

    @LogExecutionTime
    public void serve() throws InterruptedException {
        Thread.sleep(2000);
    }
}
