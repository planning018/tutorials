package com.planning.spring.schedule.shedlock;

import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author yxc
 * @date 2022/8/5 4:03 下午
 */
@SpringBootApplication
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "PT30S")
public class SpringBootShedlockApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootShedlockApplication.class, args);
    }
}
